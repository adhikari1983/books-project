package com.mybookapp.controllers;

import com.mybookapp.dto.BookDTO;
import com.mybookapp.exceptions.BookNotFoundException;
import com.mybookapp.exceptions.NegativeIdException;
import com.mybookapp.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1")
public class Restcontroller {

    @Autowired
    private BookService bookService;

    // List all books
    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> listOfBookDTO = bookService.ShowAllBooks();
        return new ResponseEntity<>(listOfBookDTO, HttpStatus.OK);
    }

    // Save a book
    @PostMapping("/books")
    public ResponseEntity<String> saveBook(@RequestBody BookDTO bookDTO) {
        bookService.saveTheBook(bookDTO);
        return new ResponseEntity<>("Book is saved successfully ", HttpStatus.CREATED);
    }

    // Find a book book where bookId = {bookId}
    @GetMapping("/books/{id}")
    public ResponseEntity<?> findBookBookId(@PathVariable int id) {
          if (id < 0) {
            throw new NegativeIdException(" Book ID cann't be Negative!!!...");
        }
        BookDTO bookDTO = bookService.findBookById(id);
        if (bookDTO != null) {
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book does not exit", HttpStatus.OK);
        }

    }

    // update a book where where id = {id}
    @PutMapping("/books")
    public ResponseEntity<String> updateTheBook(@RequestBody BookDTO bookDTO) {
        BookDTO bookDTOStatus = bookService.findBookById(bookDTO.getId());
        if (bookDTOStatus != null) {
            bookService.updateBook(bookDTO);
            return new ResponseEntity<>("Book information updated Successfully!!!....", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Book does not exit", HttpStatus.OK);
        }
    }

    // update a single field where id = {id}
    @PatchMapping("/books")
    public ResponseEntity<String> partiallyUpdateBook(@RequestBody BookDTO bookDTO) {
        BookDTO bookDTOStatus = bookService.findBookById(bookDTO.getId());
        bookService.updateBook(bookDTO);
        if (bookDTOStatus != null) {
            bookService.updateBook(bookDTO);
            return new ResponseEntity<>("Book information is partially updated!!!....", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Book does not exit", HttpStatus.OK);
        }
    }

    // delete a book where where id = {id}
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id) {
        if (id < 0) {
            throw new NegativeIdException(" Book ID cann't be Negative!!!...");
        }

        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Book information deleted Successfully!!!....", HttpStatus.OK);
        } catch (EmptyResultDataAccessException ee) {
            throw new BookNotFoundException("Deletion issue, book doesn't exit!!!...");
        } catch (Exception e) {
            return new ResponseEntity<>("Deletion issue, due to some unexpected problem!!!...", HttpStatus.OK);
        }
    }

}
