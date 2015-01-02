package com.icupad.service;

import com.icupad.repository.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractBaseService<T> implements BaseService<T> {
    private BaseRepository<T, Long> repository;

    public AbstractBaseService(BaseRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(Long id) {
        return repository.exists(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void delete(Iterable<? extends T> entities) {
        repository.delete(entities);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public T getOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<Long> ids) {
        return repository.findAll(ids);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public <S extends T> List<S> save(Iterable<S> entities) {
        return repository.save(entities);
    }
}
