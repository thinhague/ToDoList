package com.app.todolist.entrypoint.todolist.request;

import com.app.todolist.core.domain.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateToDoListRequest {
    @NotEmpty(message = "should not be empty")
    private String task;
    public ToDoList toDomain(){
        return ToDoList.builder()
                .task(this.task)
                .isChecked(false)
                .build();
    }
}
