package com.sandbox_maven.package3.service;

import com.sandbox_maven.package3.entity.Book;
import com.sandbox_maven.package3.repository.BookRepository;
import com.sandbox_maven.package3.util.CustomResponse;
import com.sandbox_maven.package3.util.CustomStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public CustomResponse<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        return new CustomResponse<>(books, CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> getBookByTitle(String title) {
        Book book = bookRepository.findBookByTitle(title).orElseThrow();
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> addBook(Book book) {
        Book newBook = bookRepository.save(book);
        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }
}
