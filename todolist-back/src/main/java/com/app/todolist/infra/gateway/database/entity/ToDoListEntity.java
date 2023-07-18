package com.app.todolist.infra.gateway.database.entity;

import com.app.todolist.core.domain.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Builder(toBuilder = true)
@Entity(name = "todolist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;
    private Boolean isChecked;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.updatedAt = ZonedDateTime.now();
        this.createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

    public ToDoList toDomain() {
        return ToDoList.builder()
                .id(this.id)
                .task(this.task)
                .isChecked(this.isChecked)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static ToDoListEntity fromDomain(final ToDoList todoList) {
        return ToDoListEntity.builder()
                .id(todoList.getId())
                .task(todoList.getTask())
                .isChecked(todoList.getIsChecked())
                .createdAt(todoList.getCreatedAt())
                .updatedAt(todoList.getUpdatedAt())
                .build();
    }
}
