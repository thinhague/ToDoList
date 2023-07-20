package com.app.todolist.core.usecase;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.gateway.ToDoListGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchToDoListTest {

    @Mock
    private ToDoListGateway toDoListGateway;

    @InjectMocks
    private SearchToDoList searchToDoList;

    @Test
    public void testSearchToDoLists() {
        PageRequest pageable = PageRequest.of(0, 20);
        Boolean isChecked = false;

        searchToDoList.search(pageable, isChecked);

        verify(toDoListGateway, times(1)).search(pageable,isChecked);
    }

    @Test
    void testSearchToDoListById(){
        final Long id = 1L;

        final ToDoList toDoList = ToDoList.builder()
                .id(id)
                .task("test task")
                .isChecked(false)
                .build();
        when(toDoListGateway.findById(id)).thenReturn(Optional.ofNullable(toDoList));

        searchToDoList.findById(id);

        verify(toDoListGateway, times(1)).findById(id);
    }

    @Test
    void testSearchToDoListByNotFound(){
        final Long id = 1L;

        when(toDoListGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> searchToDoList.findById(id));

        verify(toDoListGateway, times(1)).findById(id);
    }
}
