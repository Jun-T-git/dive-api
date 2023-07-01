package com.junt.dive.domain;

import java.util.List;

public class IdeaList {
    private final List<Idea> ideaList;

    public IdeaList(List<Idea> ideaList) {
        this.ideaList = ideaList;
    }

    public List<Idea> getIdeaList() {
        return ideaList;
    }
}
