package com.mjc.school.service.validation;

import java.util.Set;

public interface Validator<T> {
    //set of violations
    Set<String> validate(T t);
}
