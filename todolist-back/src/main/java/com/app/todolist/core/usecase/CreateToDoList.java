package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateToDoList {
    private final ToDoListGateway todoListGateway;
    @Transactional
    public ToDoList create(final ToDoList todoList){
        return todoListGateway.create(todoList);
    }
}
