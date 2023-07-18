package com.app.todolist.entrypoint.todolist.request;

import com.app.todolist.core.domain.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateToDoListRequest {
    @NotEmpty(message = "should not be empty")
    private String task;
    private Boolean isChecked;

    public ToDoList toDomain(final Long id){
        return ToDoList.builder()
                .id(id)
                .task(this.task)
                .isChecked(this.isChecked)
                .build();
    }
}
