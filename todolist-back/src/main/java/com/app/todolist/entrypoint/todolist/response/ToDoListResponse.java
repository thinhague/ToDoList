package com.app.todolist.entrypoint.todolist.response;

import com.app.todolist.core.domain.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoListResponse {
    private Long id;
    private String task;
    private Boolean isChecked;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static ToDoListResponse fromDomain(final ToDoList todoList) {
        return ToDoListResponse.builder()
                .id(todoList.getId())
                .task(todoList.getTask())
                .isChecked(todoList.getIsChecked())
                .createdAt(todoList.getCreatedAt())
                .updatedAt(todoList.getUpdatedAt())
                .build();
    }
}
