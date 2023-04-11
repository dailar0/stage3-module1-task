package com.mjc.school.service.logic;

import com.mjc.school.repository.dao.AuthorInMemoryDAOImpl;
import com.mjc.school.repository.dao.DAO;
import com.mjc.school.repository.dao.NewsInMemoryDAOImpl;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.DTO.NewsBriefDTO;
import com.mjc.school.service.DTO.NewsRichDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.InternalServerErrorException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.mapping.NewsMapper;
import com.mjc.school.service.validation.NewsValidatorImpl;
import com.mjc.school.service.validation.Validator;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class NewsService implements Service<NewsRichDTO, NewsBriefDTO, Long> {
    private final DAO<News, Long> newsDAO;
    private final DAO<Author, Long> authorDAO;
    private final Validator<NewsBriefDTO> newsValidator;
    private final NewsMapper mapper = NewsMapper.INSTANCE;

    {
        try {
            newsDAO = new NewsInMemoryDAOImpl();
            authorDAO = new AuthorInMemoryDAOImpl();
            newsValidator = new NewsValidatorImpl();
        } catch (IOException e) {
            throw new InternalServerErrorException("Unable to initialize internal database. " +
                    "Source files are missing or inaccessible.");
        }
    }

    @Override
    public NewsRichDTO create(NewsBriefDTO newsDTO) {
        validate(newsDTO);

        News news = mapper.mapBriefToNews(newsDTO);
        News savedNews = newsDAO.create(news);
        return mapper.mapNewsToRich(savedNews);
    }

    @Override
    public List<NewsRichDTO> getAll() {
        return mapper.mapNewsToRichDTOList(newsDAO.getAll());
    }

    @Override
    public NewsRichDTO getById(Long id) {
        News news = getNewsById(id);
        return mapper.mapNewsToRich(news);
    }

    @Override
    public NewsRichDTO update(Long id, NewsBriefDTO newsBriefDTO) {
        validate(newsBriefDTO);

        News news = getNewsById(id);
        news.setTitle(newsBriefDTO.getTitle());
        news.setContent(newsBriefDTO.getContent());
        news.setAuthorId(newsBriefDTO.getAuthorId());

        News savedNews = newsDAO.update(id, news);
        return mapper.mapNewsToRich(savedNews);
    }

    @Override
    public void delete(Long id) {
        try {
            newsDAO.delete(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format("News with ID %d not found.", id));
        }
    }

    private void validate(NewsBriefDTO newsBriefDTO) {
        if (!authorDAO.isExist(newsBriefDTO.getAuthorId()))
            throw new EntityNotFoundException(String.format("Author with ID %d not found.", newsBriefDTO.getAuthorId()));
        Set<String> violations = newsValidator.validate(newsBriefDTO);
        if (!violations.isEmpty()) {
            String message = Validator.formErrorMessage(violations);
            throw new ValidationException(message);
        }
    }

    private News getNewsById(Long id) {
        News news;
        try {
            news = newsDAO.getById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(String.format("News with ID %d not found.", id));
        }
        return news;
    }

}
