package com.mjc.school.service.logic;

import com.mjc.school.service.DTO.NewsBriefDTO;
import com.mjc.school.service.DTO.NewsRichDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsServiceTest {
    private final NewsService service = new NewsService();


    @Test
    void create() {
        // given
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Test news-cr", "This is a test news", 1L);

        // when
        NewsRichDTO createdNews = service.create(newsBriefDTO);

        // then
        assertNotEquals(createdNews.getId(), 0L);
        assertEquals(newsBriefDTO.getTitle(), createdNews.getTitle());
        assertEquals(newsBriefDTO.getContent(), createdNews.getContent());
        assertEquals(newsBriefDTO.getAuthorId(), createdNews.getAuthorId());
    }

    @Test
    void getAll() {
        List<NewsRichDTO> freshNewsList = service.getAll();
        assertNotEquals(freshNewsList.size(), 0);

        NewsBriefDTO newsBriefDTO1 = new NewsBriefDTO("Test news-ga1", "This is a test news", 1L);
        NewsBriefDTO newsBriefDTO2 = new NewsBriefDTO("Test news-ga2", "This is a test news", 2L);
        NewsRichDTO created1 = service.create(newsBriefDTO1);
        NewsRichDTO created2 = service.create(newsBriefDTO2);
        List<NewsRichDTO> newsList = service.getAll();

        assertTrue(newsList.containsAll(List.of(created1, created2)));
    }

    @Test
    void getById() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Test news-gbi", "This is a test news", 1L);
        NewsRichDTO createdNews = service.create(newsBriefDTO);

        NewsRichDTO retrievedNews = service.getById(createdNews.getId());

        assertEquals(createdNews, retrievedNews);
    }

    @Test
    void update() {
        // given
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Test news-u", "This is a test news", 1L);
        NewsRichDTO createdNews = service.create(newsBriefDTO);
        NewsBriefDTO updatedNewsBriefDTO = new NewsBriefDTO("Updated test news", "This is an updated test news", 2L);
        // when
        NewsRichDTO updatedNews = service.update(createdNews.getId(), updatedNewsBriefDTO);
        // then
        assertEquals(updatedNewsBriefDTO.getTitle(), updatedNews.getTitle());
        assertEquals(updatedNewsBriefDTO.getContent(), updatedNews.getContent());
        assertEquals(updatedNewsBriefDTO.getAuthorId(), updatedNews.getAuthorId());
    }

    @Test
    void delete() {
        // given
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Test news-d", "This is a test news", 1L);
        NewsRichDTO createdNews = service.create(newsBriefDTO);
        // when
        service.delete(createdNews.getId());
        List<NewsRichDTO> newsList = service.getAll();
        // then
        assertFalse(newsList.contains(createdNews));
        assertThrows(EntityNotFoundException.class, () -> service.getById(createdNews.getId()));
    }

    @Test
    public void testCreateNewsWithNonExistingAuthor() {
        // given
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Test news-dwa", "This is a test news", 21L);
        // when, then
        assertThrows(EntityNotFoundException.class, () -> service.create(newsBriefDTO));
    }

    @Test
    public void testCreateNewsWithInvalidData() {
        // given
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("TN", "TN", 1L);
        // when, then
        assertThrows(ValidationException.class, () -> service.create(newsBriefDTO));
    }

    @Test
    public void testUpdateNonExistingNews() {
        // given
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Test news-uwn", "This is a test news", 1L);
        // when, then
        assertThrows(EntityNotFoundException.class, () -> service.update(999L, newsBriefDTO));
    }

    @Test
    public void testDeleteNonExistingNews() {
        // given, when, then
        assertThrows(EntityNotFoundException.class, () -> service.delete(999L));
    }
}