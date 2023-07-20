package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateToListTest {

    @Mock
    private ToDoListGateway toDoListGateway;

    @Mock
    private SearchToDoList searchToDoList;
    @InjectMocks
    private UpdateToDoList updateToDoList;

    @Test
    void testUpdateToDoList(){
        final Long id = 1L;
        final ToDoList toDoList = ToDoList.builder()
                .id(id)
                .task("update task")
                .isChecked(true)
                .build();

        when(searchToDoList.findById(id)).thenReturn(toDoList);
        when(toDoListGateway.update(toDoList)).thenReturn(toDoList);

        final ToDoList result = updateToDoList.update(toDoList);

        assertEquals("update task", result.getTask());
        assertEquals(true, result.getIsChecked());

        verify(toDoListGateway, times(1)).update(toDoList);
    }
    @Test
    void testExceptionWhenUpdateToDoList(){
        final Long id = 1L;
        final ToDoList toDoList = ToDoList.builder()
                .id(id)
                .task("update task")
                .isChecked(true)
                .build();

        when(searchToDoList.findById(id)).thenReturn(toDoList);
        when(toDoListGateway.update(toDoList)).thenThrow(new RuntimeException());

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            updateToDoList.update(toDoList);
        });

        assertEquals("java.lang.RuntimeException", exception.getClass().getName());

        verify(toDoListGateway, times(1)).update(toDoList);
    }
}
