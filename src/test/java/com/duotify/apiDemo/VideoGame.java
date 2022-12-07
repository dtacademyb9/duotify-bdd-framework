package com.duotify.apiDemo;

public class VideoGame {

    // POJO - plain old java object


        private String name;  // the name and the type of the keys should match the instance variables in the POJO
        private String rating;
        private Integer reviewScore;
        private Integer id;
        private String category;
        private String releaseDate;


        public VideoGame(String name, String rating, Integer reviewScore, Integer id, String category, String releaseDate) {
                this.name = name;
                this.rating = rating;
                this.reviewScore = reviewScore;
                this.id = id;
                this.category = category;
                this.releaseDate = releaseDate;
        }

        public VideoGame(){}

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

        public Integer getReviewScore() {
                return reviewScore;
        }

        public void setReviewScore(Integer reviewScore) {
                this.reviewScore = reviewScore;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getReleaseDate() {
                return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
        }
}
