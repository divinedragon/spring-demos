package com.divinedragon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.divinedragon.repo.BookRepository;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info(".... Fetching books");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("1234"));
        logger.info("");
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("4567"));
        logger.info("");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("1234"));
        logger.info("");
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("4567"));
        logger.info("");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("1234"));
        logger.info("");
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("1234"));
        logger.info("");
        logger.info("");
        logger.info("");
        logger.info("");

        logger.info(".... Fetching books by author");
        logger.info("author-1234 -->" + bookRepository.getByAuthorId(1234));
        logger.info("");
        logger.info("author-2 -->" + bookRepository.getByAuthorId(2));
        logger.info("");
        logger.info("author-1 -->" + bookRepository.getByAuthorId(1));
        logger.info("");
        logger.info("author-2 -->" + bookRepository.getByAuthorId(2));
        logger.info("");
        logger.info("author-1 -->" + bookRepository.getByAuthorId(1));
        logger.info("");
        logger.info("author-1 -->" + bookRepository.getByAuthorId(1));
        logger.info("");
    }
}
