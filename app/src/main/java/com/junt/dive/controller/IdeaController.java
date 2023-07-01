package com.junt.dive.controller;

import com.junt.dive.domain.Idea;
import com.junt.dive.domain.IdeaList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ideas")
public class IdeaController {
    @GetMapping
    public IdeaList generate() {
        Idea idea = new Idea("01", "Idea1", "Hello World!");
        List<Idea> ideas = new ArrayList<>();
        ideas.add(idea);
        IdeaList ideaList = new IdeaList(ideas);
        return ideaList;
    }
}
