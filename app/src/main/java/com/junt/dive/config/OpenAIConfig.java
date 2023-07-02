package com.junt.dive.config;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.NonAzureOpenAIKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class OpenAIConfig {

    @Value("${OPEN_AI_API_KEY}")
    private String openAIApiKey;

    @Bean
    public OpenAIClient openAIClient() {
        String apiKey = openAIApiKey;
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new NonAzureOpenAIKeyCredential(apiKey))
                .buildClient();
        return client;
    }
}
