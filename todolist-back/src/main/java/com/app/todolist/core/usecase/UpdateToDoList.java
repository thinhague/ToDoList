package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateToDoList {
    private final ToDoListGateway toDoListGateway;

    public ToDoList update(ToDoList toDoList) {
        final ToDoList toDoListToUpdate = toDoListGateway.findById(toDoList.getId()).orElseThrow();

        final Boolean isChecked = toDoList.getIsChecked() != null
                ? toDoList.getIsChecked()
                : toDoListToUpdate.getIsChecked();

        final ToDoList updatedToDoList = toDoListToUpdate.toBuilder()
                .task(toDoList.getTask())
                .isChecked(isChecked)
                .build();

        return toDoListGateway.update(updatedToDoList);
    }
}
