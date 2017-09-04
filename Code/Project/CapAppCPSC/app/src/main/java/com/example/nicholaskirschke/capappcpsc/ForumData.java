package com.example.nicholaskirschke.capappcpsc;

/**
 * Created by Arya on 10/18/16.
 */

public class ForumData {
    int ID = 0;
    String TITLE = "";
    String DESCRIPTION = "";

    public int getBuilderID() {
        return this.ID;
    }
    public String getTitle(){
        return this.TITLE;
    }
    private ForumData(Builder b){
        this.ID = b.Id;
        this.TITLE = b.Title;
        this.DESCRIPTION = b.Description;

    }
    public static class Builder {
        int Id = 0;
        String Title = "";
        String Description = "";

        // Model and price required
        Builder(int Id, String title, String description) {
            this.Id = Id;
            this.Title = title;
            this.Description = description;
        }

        // the following are setters
        // notice it returns this bulder
        // makes it suitable for chaining
        Builder setDescription(String Description) {
            this.Description = Description;
            return this;
        }
        Builder setTitle(String Title){
            this.Title = Title;
            return this;
        }
        Builder setId(int Id) {
            this.Id = Id;
            return this;
        }




        // use this to actually construct Bikedata
        // without fear of partial construction
        public ForumData build() {
            return new ForumData(this);
        }
    }
}
