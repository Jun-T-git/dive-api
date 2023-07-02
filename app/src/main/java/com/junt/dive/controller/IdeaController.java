package com.junt.dive.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junt.dive.domain.Idea;
import com.junt.dive.exception.OpenAIContentException;
import com.junt.dive.service.IdeaService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
    public List<Idea> generate() throws JsonProcessingException, OpenAIContentException {
        String theme = "ChatGPTのようなAIを絡めたアプリのアイデア";
        int ideaNum = 5;

        return ideaService.generate(theme, ideaNum);
    }
}
