package com.app.todolist.core.gateway;

import com.app.todolist.core.domain.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ToDoListGateway {
    ToDoList create(ToDoList todoList);
    Optional<ToDoList> findById(Long id);
    Page<ToDoList> search(Pageable pageable, Boolean isChecked);
    void delete(Long id);
    ToDoList update(ToDoList toDoList);
}
