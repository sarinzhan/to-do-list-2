package com.example.todolist.entity;

import jakarta.persistence.*;

@Entity(name = "task")
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @PrePersist
    void onPrePersist(){
        isCompleted = false;
    }

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public Task setCompleted(Boolean completed) {
        isCompleted = completed;
        return this;
    }
}
