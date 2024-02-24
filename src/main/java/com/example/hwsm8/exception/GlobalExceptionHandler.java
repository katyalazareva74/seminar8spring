package com.example.hwsm8.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Обработчик исключений ResourceNotFoundException
     * @param exception - исключение
     * @return - возвращает код ошибки 404 и выдает сообщение клиенту, о том, что ресурс не найден
     */
    @ExceptionHandler(com.example.hwsm8.exception.ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(com.example.hwsm8.exception.ResourceNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
