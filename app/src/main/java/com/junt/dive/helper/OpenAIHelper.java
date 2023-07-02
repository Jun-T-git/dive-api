package com.junt.dive.helper;

import com.azure.ai.openai.models.ChatCompletions;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class OpenAIHelper {
    public static String getContent(ChatCompletions chatCompletions) {
        Optional<String> content = Optional.ofNullable(chatCompletions.getChoices())
                .filter(choices -> !choices.isEmpty())
                .map(choices -> choices.get(0).getMessage().getContent());
        return content.orElse(null);
    }
}
