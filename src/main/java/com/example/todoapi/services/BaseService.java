package com.example.todoapi.services;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    T getByID(Long id);
    void insertNew(T t);
    void updateOld(T t);
    void deleteOld(Long id);
}
