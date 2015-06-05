package com.icupad.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
public class FormController {

    @RequestMapping(value = "/{module}/{formName}", method = RequestMethod.GET)
    public ResponseEntity<?> getFormTemplate(@PathVariable("module") String module, @PathVariable("formName") String formName) {
        try {
            return new ResponseEntity<>(Class.forName("com.icupad." + module + ".dto." + formName).getDeclaredFields(), HttpStatus.OK);
        } catch (ClassNotFoundException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
