package com.mjc.school.service.logic;

import java.util.List;

public interface Service<T, S, U> {
    T create(S t);

    List<T> getAll();

    T getById(U id);

    T update(U id, S t);

    void delete(U id);
}
