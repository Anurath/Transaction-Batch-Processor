package com.ex.fm.wise.service;

import com.ex.fm.wise.dto.PromptRequest;
import com.ex.fm.wise.dto.TTSRequest;
import com.ex.fm.wise.dto.TTSResponse;
import com.ex.fm.wise.repositoty.WiseRepository;
import com.google.genai.Client;
import com.ex.fm.wise.entity.Transaction;
import com.google.genai.types.GenerateContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.*;

import java.util.*;

@Service
public class WiseService {

    public static final Logger LOG = LoggerFactory.getLogger(WiseService.class);

//    @Autowired
//    private WiseRepository wiseRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final Client client;

    @Autowired
    private WiseRepository wiseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${nvidia.ai.api-key}")
    private String nvidiaApiKey;

    @Value("${nvidia.ai.url}")
    private String nvidiaUrl;

    private final Client client;
    private final WebClient sarvamWebClient;

    public WiseService(Client client, WebClient sarvamWebClient) {
        this.client = client;
        this.sarvamWebClient = sarvamWebClient;
    }

    public String generateResponse() {

        List<Transaction> all = wiseRepository.findAll();

        String mainData = objectMapper.writeValueAsString(all);

        String prompt = "Analyze this transaction and provide proper insights about my data."+mainData;

        try{
            GenerateContentResponse generateContentResponse = client.models.generateContent(
                    "gemini-2.0-flash",
                    prompt,
                    null
            );
            return generateContentResponse.text();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


    public String generateNvidiaResponse(String nvidiaUrl,String nvidiaApiKey){


        List<Transaction> all = wiseRepository.findAll();

        String mainData = objectMapper.writeValueAsString(all);

        String prompt = "Rules:\n" +
                "- Use plain text only\n" +
                "- Do not use markdown, bold, bullets, hyphens, headers, or symbols\n" +
                "- Do not use tables or special characters\n" +
                "- Keep the summary under 100 characters\n" +
                "- Cover total transactions, success count, failure count, total amount, and failure reason." +mainData;

        try{

            LOG.info("Request Buildeing Started For NVIDIA API");

            RestTemplate restTemplate = new RestTemplate();


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setBearerAuth(nvidiaApiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "moonshotai/kimi-k2-thinking");

            List<Map<String, String>> messages = new ArrayList<>();
            Map<String, String> message = new HashMap<>();
            message.put("role", "user");
            message.put("content",prompt);
            messages.add(message);

            requestBody.put("messages", messages);
            requestBody.put("temperature", 1);
            requestBody.put("top_p", 0.9);
            requestBody.put("max_tokens", 16384);
            requestBody.put("stream", false);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);

            ResponseEntity<String> response = restTemplate.exchange(
                    nvidiaUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            JsonNode root = objectMapper.readTree(response.getBody());
            String content = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            LOG.info("NVIDIA Response: {}", content);
            return content;

        }catch (Throwable e){
            LOG.error("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public byte[] convertTextToSpeech() {
        try {

            String insignt = generateNvidiaResponse(nvidiaUrl, nvidiaApiKey);

            TTSRequest request = new TTSRequest(insignt, "bn-IN");

            TTSResponse response = sarvamWebClient.post()
                    .uri("/text-to-speech")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .doOnNext(err -> LOG.error("Sarvam Error: {}", err))
                                    .flatMap(err -> Mono.error(new RuntimeException("Sarvam API Error: " + err)))
                    )
                    .bodyToMono(TTSResponse.class)
                    .block();

            if (response != null && response.getAudios() != null && !response.getAudios().isEmpty()) {
                return Base64.getDecoder().decode(response.getAudios().get(0));
            }

        } catch (Exception e) {
            LOG.error("TTS conversion failed: {}", e.getMessage());
            throw new RuntimeException("TTS conversion failed: " + e.getMessage(), e);
        }
        return null;
    }
}
