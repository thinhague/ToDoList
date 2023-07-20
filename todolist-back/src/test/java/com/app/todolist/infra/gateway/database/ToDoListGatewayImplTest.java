package com.app.todolist.infra.gateway.database;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.infra.gateway.database.entity.ToDoListEntity;
import com.app.todolist.infra.repository.ToDoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoListGatewayImplTest {
    @Mock
    private ToDoListRepository toDoListRepository;

    @InjectMocks
    private ToDoListGatewayImpl toDoListGateway;

    @Test
    void shouldCreateToDoList(){
        final ToDoListEntity entity = ToDoListEntity.fromDomain(ToDoList.builder()
                .task("new task")
                .isChecked(false)
                .build());
        when(toDoListRepository.save(any(ToDoListEntity.class))).thenReturn(entity);

        final ToDoList result = toDoListGateway.create(ToDoList.builder()
                .task("new task")
                .isChecked(false)
                .build()
        );

        assertEquals("new task", result.getTask());
        assertEquals(false, result.getIsChecked());
    }

    @Test
    void shouldUpdateToDoList(){
        final ToDoListEntity entity = ToDoListEntity.fromDomain(ToDoList.builder()
                .task("update task")
                .isChecked(true)
                .build());
        when(toDoListRepository.save(any(ToDoListEntity.class))).thenReturn(entity);

        final ToDoList result = toDoListGateway.update(ToDoList.builder()
                .task("update task")
                .isChecked(true)
                .build()
        );

        assertEquals("update task", result.getTask());
        assertEquals(true, result.getIsChecked());
    }

    @Test
    void shouldDeleteToDoList(){
        final Long id = 1L;
        doNothing().when(toDoListRepository).deleteById(id);

        toDoListGateway.delete(id);

        verify(toDoListRepository, times(1)).deleteById(id);
    }
}
