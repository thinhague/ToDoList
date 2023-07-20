package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateToDoListTest {

    @Mock
    private ToDoListGateway toDoListGateway;
    @InjectMocks
    private CreateToDoList createToDoList;

    @Test
    void testCreateToDoList(){
        final ToDoList toDoList = ToDoList.builder()
                .task("test task")
                .isChecked(false)
                .build();

        when(toDoListGateway.create(toDoList)).thenReturn(toDoList);

        final ToDoList result = createToDoList.create(toDoList);
        assertEquals("test task", result.getTask());
        assertEquals(false,result.getIsChecked());
        verify(toDoListGateway, times(1)).create(toDoList);
    }
    @Test
    void testExceptionWhenCreateToDoList(){
        final ToDoList toDoList = ToDoList.builder()
                .task("test task")
                .isChecked(false)
                .build();

        when(toDoListGateway.create(toDoList)).thenThrow(new RuntimeException());

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createToDoList.create(toDoList);
        });

        assertEquals("java.lang.RuntimeException", exception.getClass().getName());

        verify(toDoListGateway, times(1)).create(toDoList);
    }
}
