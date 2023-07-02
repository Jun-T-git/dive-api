package com.junt.dive.service;

import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.junt.dive.domain.Idea;
import com.junt.dive.domain.OpenAIClientWrapper;
import com.junt.dive.exception.OpenAIContentException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final OpenAIClientWrapper client;

    public IdeaServiceImpl(OpenAIClientWrapper client) {
        this.client = client;
    }

    @Override
    public List<Idea> generate(String theme, int ideaNum) throws JsonProcessingException, OpenAIContentException {
        List<ChatMessage> chatMessages = new ArrayList<>();

        // SYSTEM
        String systemPrompt = """
            You are the idea provider.
                                               \s
            I will specify the theme and the number of items, so provide your ideas according to the following rules:
            1. Propose a wide range of ideas based on the MECE.
            2. The output must follow the json format below:
                \\`\\`\\`
                    [
                      {
                       title: string, // title of the idea.
                       description: string // description of the idea.
                      },
                      {
                       title: string, // idea title
                       description: string // idea description.
                      }
                    ]
                \\`\\`\\`
        """;
        chatMessages.add(new ChatMessage(ChatRole.SYSTEM).setContent(systemPrompt));

        // USER
        String userPrompt = String.format("「%s」を%d個提供してください。", theme, ideaNum);
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent(userPrompt));

        return client.getIdeas(chatMessages);
    }
}
