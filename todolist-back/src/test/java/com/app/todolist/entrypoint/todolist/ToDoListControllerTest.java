package com.app.todolist.entrypoint.todolist;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.usecase.CreateToDoList;
import com.app.todolist.core.usecase.DeleteToDoList;
import com.app.todolist.core.usecase.SearchToDoList;
import com.app.todolist.core.usecase.UpdateToDoList;
import com.app.todolist.entrypoint.exception.handler.ControllerExceptionHandler;
import com.app.todolist.entrypoint.todolist.request.CreateToDoListRequest;
import com.app.todolist.entrypoint.todolist.request.UpdateToDoListRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@WebAppConfiguration
@ContextConfiguration(classes = {ToDoListController.class, ControllerExceptionHandler.class})
public class ToDoListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateToDoList createToDoList;

    @MockBean
    private SearchToDoList searchToDoList;

    @MockBean
    private DeleteToDoList deleteToDoList;

    @MockBean
    private UpdateToDoList updateToDoList;

    @Test
    void testCreateToDoList() throws Exception {
        final ToDoList toDoList = ToDoList.builder()
                .id(1L)
                .task("new task")
                .isChecked(false)
                .build();
        final CreateToDoListRequest request = new CreateToDoListRequest("new task");
        when(createToDoList.create(any())).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.post("/todolist/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.task").value("new task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isChecked").value(false));
    }

    @Test
    void testCreateToDoListWithInvalidRequest() throws Exception {

        final CreateToDoListRequest request = new CreateToDoListRequest("");
        when(createToDoList.create(any())).thenThrow(NullPointerException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/todolist/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateToDoList() throws Exception {
        final ToDoList toDoList = ToDoList.builder()
                .id(1L)
                .task("new task")
                .isChecked(false)
                .build();
        final UpdateToDoListRequest request = new UpdateToDoListRequest("new task",false);
        when(updateToDoList.update(any())).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.put("/todolist/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.task").value("new task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isChecked").value(false));
    }
    @Test
    void testUpdateToDoListWithInvalidRequest() throws Exception {
        final UpdateToDoListRequest request = new UpdateToDoListRequest("",false);
        when(updateToDoList.update(any())).thenThrow(NullPointerException.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/todolist/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSearchToDoList() throws Exception{
        final ToDoList toDoList = ToDoList.builder()
                .id(1L)
                .task("search task")
                .isChecked(false)
                .build();

        Boolean isChecked = false;

        List<ToDoList> toDoLists = Arrays.asList(toDoList);
        Page<ToDoList> mockPage = new PageImpl<>(toDoLists);

        when(searchToDoList.search(any(Pageable.class), any(Boolean.class))).thenReturn(mockPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/todolist/search?isChecked=false")
                        .param("isChecked",isChecked.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].task").value("search task"))
                .andExpect(jsonPath("$.content[0].isChecked").value(false))
                .andExpect(jsonPath("$.content.length()").value(toDoLists.size()));
    }

    @Test
    void testSearchToDoListWithInvalidRequest() throws Exception {
        Boolean isChecked = false;
        when(updateToDoList.update(any())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/todolist/search?isChecked=test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("isChecked",isChecked.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSearchToDoListById() throws Exception{
        final Long id = 1L;
        final ToDoList toDoList = ToDoList.builder()
                .id(1L)
                .task("search task")
                .isChecked(false)
                .build();


        when(searchToDoList.findById(id)).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.task").value("search task"))
                .andExpect(jsonPath("$.isChecked").value(false));
    }

    @Test
    void testSearchToDoLisByIdtWithInvalidRequest() throws Exception {
        final Long id = 1L;

        when(searchToDoList.findById(id)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteToDoList() throws Exception {
        doNothing().when(deleteToDoList).delete(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/todolist/1/delete"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTodoListWithInvalidId() throws Exception {
        doThrow(EmptyResultDataAccessException.class).when(deleteToDoList).delete(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/todolist/1000/delete"))
                .andExpect(status().isNoContent());
    }

}
