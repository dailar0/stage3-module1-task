package com.mjc.school.service.validation;

import com.mjc.school.service.DTO.NewsBriefDTO;

import java.util.HashSet;
import java.util.Set;

public class NewsValidatorImpl implements Validator<NewsBriefDTO> {
    @Override
    public Set<String> validate(NewsBriefDTO newsBriefDTO) {
        HashSet<String> violations = new HashSet<>();

        if (newsBriefDTO == null) {
            violations.add("All data is required.");
            return violations;
        }

        String title = newsBriefDTO.getTitle();
        if (title == null || title.length() < 5 || title.length() > 30)
            violations.add("Title should have length from 5 to 30.");
        String content = newsBriefDTO.getContent();
        if (content == null || content.length() < 5 || content.length() > 255)
            violations.add("Content should have length from 5 to 255.");

        return violations;
    }
}
