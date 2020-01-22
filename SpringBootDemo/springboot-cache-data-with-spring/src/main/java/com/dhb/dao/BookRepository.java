package com.dhb.dao;

import com.dhb.entity.Book;

public interface BookRepository {

    Book getByIsbn(String isbn);

}
