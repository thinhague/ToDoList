package com.app.todolist.entrypoint.todolist;

import com.app.todolist.core.domain.ToDoList;
import com.app.todolist.core.usecase.CreateToDoList;
import com.app.todolist.core.usecase.DeleteToDoList;
import com.app.todolist.core.usecase.SearchToDoList;
import com.app.todolist.core.usecase.UpdateToDoList;
import com.app.todolist.entrypoint.todolist.request.CreateToDoListRequest;
import com.app.todolist.entrypoint.todolist.request.UpdateToDoListRequest;
import com.app.todolist.entrypoint.todolist.response.ToDoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class ToDoListController {
    private final CreateToDoList createTodoList;
    private final SearchToDoList searchToDoList;
    private final DeleteToDoList deleteToDoList;
    private final UpdateToDoList updateToDoList;

    @PostMapping("/create")
    public ResponseEntity<ToDoListResponse> post(
            @RequestBody @Valid final CreateToDoListRequest request
    ){
        final ToDoList toDoList = createTodoList.create(request.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ToDoListResponse.fromDomain(toDoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoListResponse> getById(@PathVariable final Long id){
        final ToDoList toDoList = searchToDoList.findById(id);
        return ResponseEntity.ok(ToDoListResponse.fromDomain(toDoList));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ToDoListResponse>> search(
            final Pageable pageable,
            @RequestParam(required = false) final Boolean isChecked
    ){
        final Page<ToDoListResponse> toDoLists = searchToDoList.search(pageable,isChecked)
                .map(ToDoListResponse::fromDomain);
        return ResponseEntity.ok(toDoLists);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id){
        deleteToDoList.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<ToDoListResponse> put(
            @PathVariable final Long id,
            @RequestBody @Valid UpdateToDoListRequest request
    ){
        final ToDoList toDoList = updateToDoList.update(request.toDomain(id));
        return ResponseEntity.ok(ToDoListResponse.fromDomain(toDoList));
    }


}
