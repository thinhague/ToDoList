package com.app.todolist.infra.repository;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.infra.gateway.database.entity.ToDoListEntity;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Long> {

}
