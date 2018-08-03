package com.divinedragon.repo.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.divinedragon.domain.Author;
import com.divinedragon.domain.Book;
import com.divinedragon.repo.BookRepository;

@Component
public class SimpleBookRepository implements BookRepository {

    private List<Book> books;

    @Autowired
    private CacheManager cacheManager;

    public SimpleBookRepository() {

        final Author author1 = Author.builder().id(1).name("Author1").build();
        final Author author2 = Author.builder().id(2).name("Author2").build();

        //F-
        books = Arrays.asList(
                Book.builder().isbn("1234").title("Some book").author(author1).build(),
                Book.builder().isbn("4567").title("Some other book").author(author2).build()
        );
        //F+
    }

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return books.stream().filter(o -> o.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    @Cacheable(value = "books")
    @Override
    public Book getByAuthorId(int id) {
        simulateSlowService();
        Book b = books.stream().filter(o -> o.getAuthor().getId() == id).findFirst().orElse(null);

        if (b != null && b.getAuthor() != null) {
            Cache cache = cacheManager.getCache("books");
            cache.put(b.getAuthor().getId(), b);
        }

        return b;
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (final InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
