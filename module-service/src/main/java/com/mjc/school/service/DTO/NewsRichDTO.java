package com.mjc.school.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NewsRichDTO {
    private final long id;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime lastUpdateDate;
    private final long authorId;
}
