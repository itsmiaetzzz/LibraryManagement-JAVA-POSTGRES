package org.example;
public class Author {
    private int idAuthor;
    private String authorName;
    private char sex;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Author(int idAuthor, String authorName, char sex) {
        this.idAuthor = idAuthor;
        this.authorName = authorName;
        this.sex = sex;

    }
}
