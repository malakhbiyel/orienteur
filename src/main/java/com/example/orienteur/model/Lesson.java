package com.example.orienteur.model;

import jakarta.persistence.*;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private long note;
    public Lesson(int id, String title, long note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }
    public Lesson(String title, long note) {
        this.title = title;
        this.note = note;
    }

    public Lesson() {

    }

    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public long getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(long note) {
        this.note = note;
    }

    public void setId(long id) {
        this.id = id;
    }
}
