package com.mjc.school.service.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsRelevantDTO {
    private final String title;
    private final String content;
    private final long authorId;
}
