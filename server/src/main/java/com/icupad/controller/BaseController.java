package com.icupad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface BaseController<T> {
    @RequestMapping(method = RequestMethod.GET)
    Iterable<T> index();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    Map<String, Long> create(@RequestBody T t);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    T show(@PathVariable Long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void update(@PathVariable Long id, @RequestBody T t);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id);
}
