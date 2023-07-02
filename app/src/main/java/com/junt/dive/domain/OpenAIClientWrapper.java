package com.junt.dive.domain;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junt.dive.exception.OpenAIContentException;
import com.junt.dive.helper.OpenAIHelper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OpenAIClientWrapper {

    private final OpenAIClient client;

    private static final String deploymentOrModelId = "gpt-3.5-turbo";

    public OpenAIClientWrapper(OpenAIClient client) {
        this.client = client;
    }

    public List<Idea> getIdeas(List<ChatMessage> chatMessages) throws JsonProcessingException, OpenAIContentException {
        ChatCompletions chatCompletions = client.getChatCompletions(deploymentOrModelId, new ChatCompletionsOptions(chatMessages));

        String jsonString = OpenAIHelper.getContent(chatCompletions);

        if (jsonString == null) {
            throw new OpenAIContentException();
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Idea> ideas = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, Idea.class));

        return ideas;
    }
}
