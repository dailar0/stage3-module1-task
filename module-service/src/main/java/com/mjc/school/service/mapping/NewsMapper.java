package com.mjc.school.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    String datePattern = "yyyy-MM-dd'T'HH:mm'Z'";

}
