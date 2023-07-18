package com.app.todolist.entrypoint.exception.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private ZonedDateTime timestamp;
    private Integer statusCode;
    private String message;
}
