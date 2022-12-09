package com.duotify.apiDemo.pojos;

import java.util.List;

public class Place {


    private List<Candidate> candidates;
    private String status;


    public Place(){}

    public Place(List<Candidate> candidates, String status) {
        this.candidates = candidates;
        this.status = status;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Place{" +
                "candidates=" + candidates +
                ", status='" + status + '\'' +
                '}';
    }
}
