package com.mjc.school.repository.dao;

import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.News;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

public class NewsInMemoryDAOImpl implements DAO<News, Long> {

    private final Collection<News> newsList;

    public NewsInMemoryDAOImpl() throws IOException {
        DataSource dataSource = DataSource.getInstance();
        newsList = dataSource.getNewsStorage();
    }

    @Override
    public News create(News news) {
        newsList.add(news);
        return news;
    }

    @Override
    public Collection<News> getAll() {
        return Collections.unmodifiableCollection(newsList);
    }

    @Override
    public News getById(Long id) {
        return newsList.stream()
                .filter(news -> news.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public News update(Long id, News news) {
        News storedNews = getById(id);
        storedNews.setTitle(news.getTitle());
        storedNews.setContent(news.getContent());
        storedNews.setAuthorId(news.getAuthorId());
        storedNews.setLastUpdateDate(LocalDateTime.now());
        return storedNews;
    }

    @Override
    public void delete(Long id) {
        if (!newsList.removeIf(news -> news.getId() == id)) {
            throw new NoSuchElementException("No value present");
        }
    }
}
