package com.app.todolist.infra.gateway.database;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import com.app.todolist.infra.gateway.database.entity.ToDoListEntity;
import com.app.todolist.infra.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

import static com.app.todolist.infra.gateway.database.entity.ToDoListEntity.fromDomain;

@Component
@RequiredArgsConstructor
public class ToDoListGatewayImpl implements ToDoListGateway {

    private final ToDoListRepository toDoListRepository;
    @Override
    public ToDoList create(final ToDoList todoList) {
        return toDoListRepository.save(fromDomain(todoList)).toDomain();
    }

    @Override
    public Optional<ToDoList> findById(final Long id) {
        return toDoListRepository.findById(id).map(ToDoListEntity::toDomain);
    }

    @Override
    public Page<ToDoList> search(final Pageable pageable, final Boolean isChecked) {
       var toDoList = new ToDoListEntity();
        toDoList.setIsChecked(isChecked);
        Example<ToDoListEntity> example = Example.of(toDoList);
        return toDoListRepository
                .findAll(example,pageable).map(ToDoListEntity::toDomain);
    }

    @Override
    public void delete(Long id) {
        toDoListRepository
                .deleteById(id);
    }

    @Override
    public ToDoList update(ToDoList toDoList) {
        return toDoListRepository.save(fromDomain(toDoList)).toDomain();
    }
}
