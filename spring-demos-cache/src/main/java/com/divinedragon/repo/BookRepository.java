package com.divinedragon.repo;

import com.divinedragon.domain.Book;

public interface BookRepository {

    Book getByIsbn(String isbn);

    Book getByAuthorId(int id);

}
