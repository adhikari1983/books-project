package com.mybookapp.service;

import java.util.List;

import com.mybookapp.dto.BookDTO;

public interface BookService {

    BookDTO findBookById(int id);

    List<BookDTO> ShowAllBooks();

    void saveTheBook(BookDTO bookDTO);

    void updateBook(BookDTO bookDTO);

    void deleteBook(int id);

}
