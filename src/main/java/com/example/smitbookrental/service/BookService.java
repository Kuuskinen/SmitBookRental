package com.example.smitbookrental.service;

import com.example.smitbookrental.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public void addBook(BookEntity book) {
        bookRepository.save(book);
    }

    public void changeBook(Long id, String name, String author, Boolean reservable) {
        BookEntity bookEntity = bookRepository.getById(id);
        bookEntity.setName(name);
        bookEntity.setAuthor(author);
        bookEntity.setReservable(reservable);

        bookRepository.save(bookEntity);
    }

    public void changeStatus(String newStatus, Long bookId) {
        bookRepository.changeStatus(newStatus, bookId);
    }
}
