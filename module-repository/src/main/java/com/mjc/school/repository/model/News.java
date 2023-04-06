package com.mjc.school.repository.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class News {
    private final long id;
    private String title;
    private String content;
    private final LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private long authorId;
}
