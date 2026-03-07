package com.ex.fm.wise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TTSRequest {
    @JsonProperty("text")
    private String text;

    @JsonProperty("target_language_code")
    private String targetLanguageCode;

    public TTSRequest(String text, String targetLanguageCode) {
        this.text = text;
        this.targetLanguageCode = targetLanguageCode;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getTargetLanguageCode() { return targetLanguageCode; }
    public void setTargetLanguageCode(String code) { this.targetLanguageCode = code; }
}
