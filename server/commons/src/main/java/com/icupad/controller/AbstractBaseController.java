package com.icupad.controller;

import com.icupad.domain.BaseEntity;
import com.icupad.service.BaseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class AbstractBaseController<T extends BaseEntity> {
    private BaseService<T> service;

    public AbstractBaseController(BaseService<T> service) {
        this.service = service;
    }

    public Iterable<T> index() {
        return service.findAll();
    }

    public Map<String, Long> create(@RequestBody T t) {
        service.save(t);
        Map<String, Long> response = new HashMap<>();
        response.put("id", t.getId());

        return response;
    }

    public T show(@PathVariable Long id) {
        return service.getOne(id);
    }

    public void update(@PathVariable Long id, @RequestBody T movie) {
        movie.setId(id);
        service.save(movie);
    }

    public void destroy(@PathVariable Long id) {
        service.delete(id);
    }
}
