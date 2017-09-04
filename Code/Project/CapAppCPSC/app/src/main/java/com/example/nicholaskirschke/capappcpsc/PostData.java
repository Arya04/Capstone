package com.example.nicholaskirschke.capappcpsc;

/**
 * Created by Arya on 10/18/16.
 */

public class PostData {
    int ID = 0;
    int IDTHREAD = 0;
    int IDUSER = 0;
    String NAME = "";
    String POST = "";
    String CREATEDAT = "";
    String CLASSYEAR = "";
    String BIO = "";

    public int getBuilderID() {
        return this.ID;
    }
    public String getBio() {
        return this.BIO;
    }
    public String getClassYear() {
        return this.CLASSYEAR;
    }
    private PostData(Builder b){
        this.ID = b.Id;
        this.IDTHREAD = b.IdThread;
        this.IDUSER = b.IdUser;
        this.NAME = b.name;
        this.POST = b.post;
        this.CREATEDAT = b.createdAt;
        this.CLASSYEAR = b.classYear;
        this.BIO = b.bio;


    }

    public String getName() {
        return NAME;
    }

    public static class Builder {
        int Id = 0;
        int IdThread = 0;
        int IdUser = 0;
        String name = "";
        String post = "";
        String createdAt = "";
        String classYear = "";
        String bio = "";


        // Model and price required
        Builder(int Id, int IdThread,int IdUser, String name, String post, String createdAt, String classYear, String bio) {
            this.Id = Id;
            this.IdThread = IdThread;
            this.IdUser = IdUser;
            this.name = name;
            this.post = post;
            this.createdAt = createdAt;
            this.classYear = classYear;
            this.bio = bio;
        }

        // the following are setters
        // notice it returns this bulder
        // makes it suitable for chaining
        Builder setId(int Id) {
            this.Id = Id;
            return this;
        }
        Builder setIdThread(int IdThread){
            this.IdThread = IdThread;
            return this;
        }

        Builder setIdUser(int idUser) {
            this.IdUser = idUser;
            return this;
        }

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setPost(String post) {
            this.post = post;
            return this;
        }

        Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public PostData build() {
            return new PostData(this);
        }
    }
}
