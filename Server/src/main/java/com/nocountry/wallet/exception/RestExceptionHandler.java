package com.nocountry.wallet.exception;

import com.nocountry.wallet.models.response.ApiErrorDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * With this ExceptionHandler we can handling exceptions and response a JSON with error messages.
     * In the future maybe we can override others exception in "ResponseEntityExceptionHandler"
     * @param ex (Exception)
     * @return a JSON with error messages
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Throwable.class)
    protected ResponseEntity<Object> handleThrowable(Throwable ex, WebRequest req) {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                Arrays.asList("")
        );
        return handleExceptionInternal((Exception) ex, apiErrorDTO, new HttpHeaders(), apiErrorDTO.getStatus(), req);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(Throwable ex, WebRequest req) {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("")
        );
        return handleExceptionInternal((Exception) ex, apiErrorDTO, new HttpHeaders(), apiErrorDTO.getStatus(), req);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExpiredJwtException.class)
    protected ResponseEntity<Object> handleExpiredToken(Throwable ex, WebRequest req) {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                Arrays.asList("")
        );
        return handleExceptionInternal((Exception) ex, apiErrorDTO, new HttpHeaders(), apiErrorDTO.getStatus(), req);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
        return new ResponseEntity<>(apiErrorDTO, HttpStatus.BAD_REQUEST);
    }
}

