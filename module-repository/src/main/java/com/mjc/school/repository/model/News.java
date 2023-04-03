package com.mjc.school.repository.model;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class News {

    private final long id;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime lastUpdateDate;
    private final Author author;
}
