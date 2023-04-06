package com.mjc.school.repository.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode(exclude = "name")
public class Author {
    private final long id;
    private String name;
}
