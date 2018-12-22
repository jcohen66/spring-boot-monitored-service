package com.example.clientdemo.web;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

class MyApplicationException extends Exception {

}

@RestController
public class WebController {
    @GetMapping(path = "/", produces="application/json")
    public ResponseEntity<Object> rootPath() {
        return new ResponseEntity<>("Spring Client Application",  HttpStatus.OK);
    }

    @GetMapping(path = "/entity", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sayHello()
    {
        //Get data from service layer into entityList.

        List<JSONObject> entities = new ArrayList<JSONObject>();
            JSONObject entity = new JSONObject();
            entity.put("aa", "bb");
            entities.add(entity);

        return new ResponseEntity<Object>(entities, HttpStatus.OK);
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