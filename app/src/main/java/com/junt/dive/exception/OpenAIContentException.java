package com.junt.dive.exception;

public class OpenAIContentException extends Exception {
    public OpenAIContentException() {
        super("OpenAI Content is Invalid.");
    }
}
