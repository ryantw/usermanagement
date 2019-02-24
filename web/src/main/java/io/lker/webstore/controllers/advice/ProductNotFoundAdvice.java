package io.lker.webstore.controllers.advice;

import io.lker.webstore.usermanagement.util.exceptions.ProductNotFoundException;
import io.lker.webstore.usermanagement.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ProductNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(ProductNotFoundException ex) {
        log.error(ex.getMessage());
        return "{ \"error\": \"Could not find product.\" }";
    }
}
