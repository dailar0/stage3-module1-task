package com.mjc.school.service.logic;

import java.util.List;
import java.util.Optional;

public interface Service<T, S, U> {
    T create(S t);

    List<T> getAll();

    Optional<T> getById(U id);

    T update(U id, T t);

    boolean delete(U id);
}
