package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteToDoList {
    private final ToDoListGateway toDoListGateway;
    public void delete(final Long id){
        toDoListGateway.delete(id);
    }
}
