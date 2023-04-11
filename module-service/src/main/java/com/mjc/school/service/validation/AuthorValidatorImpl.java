package com.mjc.school.service.validation;

import com.mjc.school.service.DTO.AuthorDTO;

import java.util.HashSet;
import java.util.Set;

public class AuthorValidatorImpl implements Validator<AuthorDTO> {

    @Override
    public Set<String> validate(AuthorDTO authorDTO) {
        HashSet<String> violations = new HashSet<>();

        int titleSize = authorDTO.getName().length();
        if (titleSize<3 || titleSize>15)
            violations.add("Name should have length from 5 to 30.");

        return violations;
    }
}
