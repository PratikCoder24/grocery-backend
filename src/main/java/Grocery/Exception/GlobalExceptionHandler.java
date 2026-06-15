package Grocery.Exception;

import Grocery.DTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(
               new ErrorResponseDTO(
                       LocalDateTime.now().toString(),
                       HttpStatus.NOT_FOUND.value(),
                       "Not Found",
                       e.getMessage()
               ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleInsufficientStockException(InsufficientStockException e){
        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleDuplicateResourceException(DuplicateResourceException e){
        return new ResponseEntity<>(
               new ErrorResponseDTO(
                       LocalDateTime.now().toString(),
                       HttpStatus.CONFLICT.value(),
                       "Conflict",
                       e.getMessage()
               ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidJson(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        "Invalid JSON format or missing request body"
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        LocalDateTime.now().toString(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        "Something went wrong.Please try again."
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
