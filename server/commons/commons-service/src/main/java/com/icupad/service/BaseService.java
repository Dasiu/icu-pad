package com.icupad.service;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface BaseService<T> {
    boolean exists(Long id);

    long count();

    void delete(Long id);

    void delete(T t);

    void delete(Iterable<? extends T> t);

    void deleteAll();

    T getOne(Long id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAll(Iterable<Long> ids);

    <S extends T> S save(S entity);

    <S extends T> List<S> save(Iterable<S> entities);
}
