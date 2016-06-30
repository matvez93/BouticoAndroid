package com.Boutico.Boutico.core.promotion;

public class Promotion {
    private String title;
    private double percentage;
    private String comment;

    public Promotion(String title, double percentage, String comment){
        this.title=title;
        this.percentage=percentage;
        this.comment=comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
