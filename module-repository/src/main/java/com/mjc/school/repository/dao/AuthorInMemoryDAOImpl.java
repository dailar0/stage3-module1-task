package com.mjc.school.repository.dao;

import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.Author;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

public class AuthorInMemoryDAOImpl implements DAO<Author, Long> {
    private final Collection<Author> authors;


    public AuthorInMemoryDAOImpl() throws IOException {
        DataSource dataSource = DataSource.getInstance();
        authors = dataSource.getAuthorStorage();
    }

    @Override
    public Author create(Author author) {
        authors.add(author);
        return author;
    }

    @Override
    public Collection<Author> getAll() {
        return Collections.unmodifiableCollection(authors);
    }

    @Override
    public Author getById(Long id) {
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Author update(Long id, Author author) {
        Author storedAuthor = getById(id);
        storedAuthor.setName(author.getName());
        return storedAuthor;
    }

    @Override
    public void delete(Long id) {
        if (!authors.removeIf(author -> author.getId() == id)) {
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public boolean isExist(Long id) {
        for (Author author : authors) {
            if (author.getId() == id)
                return true;
        }
        return false;
    }
}
