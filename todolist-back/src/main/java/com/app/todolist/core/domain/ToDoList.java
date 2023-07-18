package com.app.todolist.core.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder(toBuilder = true)
public class ToDoList {
    Long id;
    String task;
    Boolean isChecked;
    @EqualsAndHashCode.Exclude
    ZonedDateTime createdAt;
    @EqualsAndHashCode.Exclude
    ZonedDateTime updatedAt;
}
