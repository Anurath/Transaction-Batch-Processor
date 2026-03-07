package com.ex.fm.wise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TTSResponse {
    @JsonProperty("audios")
    private List<String> audios;

    @JsonProperty("request_id")
    private String requestId;

    public List<String> getAudios() { return audios; }
    public void setAudios(List<String> audios) { this.audios = audios; }
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
}
