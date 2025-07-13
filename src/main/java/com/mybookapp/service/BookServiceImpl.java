package com.mybookapp.service;

import com.mybookapp.books.BookEntity;
import com.mybookapp.dto.BookDTO;
import com.mybookapp.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    // To get a single book by bookId
    @Override
    public BookDTO findBookById(int id) {
        Optional<BookEntity> optional = bookRepository.findById(id);

        if (optional.isPresent()) {
            BookEntity bookEntity = optional.get();
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(bookEntity, bookDTO);
            return bookDTO;
        }
        return null;
    }

    // To get all the books as list of books
    @Override
    public List<BookDTO> ShowAllBooks() {

        List<BookEntity> listofBookEntities = bookRepository.findAll();

        List<BookDTO> listodBookDtos = new ArrayList<>();

        if (!listofBookEntities.isEmpty()) {
            for (BookEntity entity : listofBookEntities) {
                BookDTO bookDTO = new BookDTO();
                BeanUtils.copyProperties(entity, bookDTO);
                listodBookDtos.add(bookDTO);
            }
        }
        return listodBookDtos;
    }

    // To save  a single book
    @Override
    public void saveTheBook(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookDTO, bookEntity);
        bookRepository.save(bookEntity);
    }

    // To save as a whole/partially updted book
    @Override
    public void updateBook(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookDTO, bookEntity);
        bookRepository.save(bookEntity);
    }

    // To delete the book
    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
