package com.app.todolist.core.usecase;

import com.app.todolist.core.gateway.ToDoListGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteToDoListTest {

    @Mock
    private ToDoListGateway toDoListGateway;
    @InjectMocks
    private DeleteToDoList deleteToDoList;

    @Test
    void testDeleteToDoList(){
        final Long id = 1L;
        doNothing().when(toDoListGateway).delete(id);

        deleteToDoList.delete(id);

        verify(toDoListGateway, times(1)).delete(id);
    }
}
