package com.mjc.school.service.DTO;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class NewsBriefDTO {
    private final String title;
    private final String content;
    private final long authorId;
}
