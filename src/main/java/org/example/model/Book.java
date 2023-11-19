package org.example;

import java.sql.Date;
public class Book {
    private int id;
    private String bookName;
    private int authorId;
    private Topic topic;
    private int pageNumbers;
    private String releaseDate;

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Book(int id, String bookName, int authorId, Topic topic, int pageNumbers, String releaseDate) {
        this.id = id;
        this.bookName = bookName;
        this.authorId = authorId;
        this.topic = topic;
        this.pageNumbers = pageNumbers;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


}

