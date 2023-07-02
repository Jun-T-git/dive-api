package com.junt.dive.service;

import com.azure.ai.openai.models.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.junt.dive.domain.Idea;
import com.junt.dive.domain.OpenAIClientWrapper;
import com.junt.dive.exception.OpenAIContentException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class IdeaServiceImplTest {

    @Mock
    private OpenAIClientWrapper client;

    @InjectMocks
    private IdeaServiceImpl target;

    @Test
    public void testGenerate() {
        try {
            // setup:
            String theme = "theme";
            int ideaNum = 2;
            Idea idea1 = new Idea("id1", "タイトル1", "概要1");
            Idea idea2 = new Idea("id2", "タイトル2", "概要2");
            List<Idea> expected = new ArrayList<>(Arrays.asList(idea1, idea2));
            ArgumentMatcher<List<ChatMessage>> matcher = argument -> {
                Assertions.assertEquals(2, argument.size());
                // メッセージにtheme,ideaNumが含まれているか
                Assertions.assertTrue(argument.get(1).getContent().contains(String.valueOf(ideaNum)));
                Assertions.assertTrue(argument.get(1).getContent().contains(theme));
                return true;
            };

            Mockito.doReturn(expected)
                    .when(client)
                    .getIdeas(ArgumentMatchers.argThat(matcher));

            // when:
            List<Idea> result = target.generate(theme, ideaNum);

            // then:
            Assertions.assertEquals(expected, result);
        } catch (JsonProcessingException | OpenAIContentException e) {
            Assertions.fail("Test failed due to exception:" + e.getMessage());
        }
    }
}
