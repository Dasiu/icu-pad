package com.icupad.controller;

import com.icupad.domain.BaseEntity;
import com.icupad.service.BaseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class AbstractBaseController<T extends BaseEntity> implements BaseController<T> {
    private BaseService<T> service;

    public AbstractBaseController(BaseService<T> service) {
        this.service = service;
    }

    @Override
    public Iterable<T> index() {
        return service.findAll();
    }

    @Override
    public Map<String, Long> create(@RequestBody T t) {
        service.save(t);
        Map<String, Long> response = new HashMap<>();
        response.put("id", t.getId());

        return response;
    }

    @Override
    public T show(@PathVariable Long id) {
        return service.getOne(id);
    }

    @Override
    public void update(@PathVariable Long id, @RequestBody T movie) {
        movie.setId(id);
        service.save(movie);
    }

    @Override
    public void destroy(@PathVariable Long id) {
        service.delete(id);
    }
}
