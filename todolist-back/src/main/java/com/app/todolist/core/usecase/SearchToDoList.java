package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import com.app.todolist.entrypoint.todolist.response.ToDoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchToDoList {
    private final ToDoListGateway toDoListGateway;
    public ToDoList findById(final Long id){
        return toDoListGateway.findById(id).orElseThrow();
    }
    public Page<ToDoList> search(final Pageable pageable, final Boolean isChecked){
        return toDoListGateway.search(pageable,isChecked);
    }
}
