package com.spearhead.springsec.authorws.author.model;

public class Author {
    private String authorId;
    private String name;
    private String email;

    public Author() {
    }

    public Author(String authorId, String name, String email) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
