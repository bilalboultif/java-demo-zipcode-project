package com.kenzie.app.zipcode.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GameDTO {

    @JsonProperty("clues")
    private List<Clues> clues;
    @JsonProperty("id")
    private int id;

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
