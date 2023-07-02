package com.junt.dive.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junt.dive.domain.Idea;
import com.junt.dive.exception.OpenAIContentException;
import java.util.List;

public interface IdeaService {
    List<Idea> generate(String theme, int ideaNum) throws JsonProcessingException, OpenAIContentException;
}