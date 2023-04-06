package com.mjc.school.repository.dao;

import java.util.Collection;

public interface DAO <T,S>{
    T create(T t);
    Collection<T> getAll();
    T getById(S id);
    T update(S id, T t);
    void delete(S id);
}
