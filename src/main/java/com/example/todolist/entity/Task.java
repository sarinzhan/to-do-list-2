package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "task")
@Data
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
}
