package com.example.clientdemo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

class MyApplicationException extends Exception {

}

@RestController
public class WebController {
    @GetMapping(path = "/", produces="application/json")
    public ResponseEntity<Object> rootPath() {
        return new ResponseEntity<>("Spring Client Application",  HttpStatus.OK);
    }


    @ExceptionHandler(MyApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAppException(MyApplicationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        return ex.getMessage();
    }
}
;