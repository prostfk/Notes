package by.prostrmk.notes.model.entity;

import javax.persistence.Entity;

@Entity
public class Note {

    private String id;
    private String head;
    private String body;
    private User user;

    public Note() {
    }

    public Note(String id, String head, String body, User user) {
        this.id = id;
        this.head = head;
        this.body = body;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
