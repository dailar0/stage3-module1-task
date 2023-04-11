package com.mjc.school.service.mapping;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.DTO.NewsBriefDTO;
import com.mjc.school.service.DTO.NewsRichDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper()
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(target = "id", expression = "java(new java.util.Random().nextLong())")
    @Mapping(target = "createDate", expression = "java(LocalDateTime.now())", dateFormat = "yyyy-MM-dd'T'HH:mm'Z'")
    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now())", dateFormat = "yyyy-MM-dd'T'HH:mm'Z'")
    News mapBriefToNews(NewsBriefDTO dto);

    NewsRichDTO mapNewsToRich(News news);

    List<NewsRichDTO> mapNewsToRichDTOList(Collection<News> newsCollection);
}

