package com.duotify.apiDemo.pojos;

public class Candidate {

    private String formatted_address;
    private String name;
    private String rating;


    public Candidate(){}

    public Candidate(String formatted_address, String name, String rating) {
        this.formatted_address = formatted_address;
        this.name = name;
        this.rating = rating;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Candidate{" +
                "formatted_address='" + formatted_address + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
