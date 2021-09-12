package com.example.smitbookrental.controller;

import com.example.smitbookrental.entity.ReservationEntity;
import com.example.smitbookrental.request.BookRequest;
import com.example.smitbookrental.entity.BookEntity;
import com.example.smitbookrental.request.StatusRequest;
import com.example.smitbookrental.service.BookService;
import com.example.smitbookrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    ReservationService reservationService;

    //STATUS IS IMPORTANT
    @GetMapping("/allBooks")
    public List<BookEntity> getAllBooks() {
        List<BookEntity> allBooks = bookService.getAllBooks();
        List<BookEntity> allReservableBooks = new ArrayList<>();
        for (BookEntity allBook : allBooks) {
            if (allBook.getReservable() && allBook.getStatus().equals("Available")) {
                allReservableBooks.add(allBook);
            }
        }
        return allReservableBooks;
    }

    @GetMapping("/ownerBooks/{id}")
    public List<BookEntity> getOwnerBooks(@PathVariable String id) {
        int idAsInt = Integer.parseInt(id);

        List<BookEntity> books = bookService.getAllBooks();
        List<BookEntity> ownerBooks = new ArrayList<>();
        for (BookEntity allBook : books) {
            if (allBook.getOwner() == idAsInt) {
                ownerBooks.add(allBook);
            }
        }
        return ownerBooks;
    }

    @GetMapping("/get_one_book/{id}")
    public BookEntity getOneBook(@PathVariable String id) {
        long idAsLong = Long.parseLong(id);
        return bookService.getBook(idAsLong);
    }

    //CHANGES STATUS
    @PostMapping("/new_book")
    public void addBook(@RequestBody BookRequest request) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(request.getName());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setOwner(1);
        bookEntity.setReservable(request.getReservable());
        bookEntity.setStatus("Available");
        bookService.addBook(bookEntity);
    }

    @PostMapping("/change_book")
    public void changeBook(@RequestBody BookRequest request) {
        Long id = request.getId();
        String name = request.getName();
        String author = request.getAuthor();
        Boolean reservable = request.getReservable();
        bookService.changeBook(id, name, author, reservable);
    }

    @PostMapping("/book_status_update")
    public void changeBookStatus(@RequestBody StatusRequest request) {
        System.out.println("REQUEST RECEIVED");
        Long id = request.getId();
        System.out.println(id);

        String currentStatus = bookService.getBook(id).getStatus();
        String newStatus;
        switch (currentStatus) {
            case "Reserved":
                newStatus = "Sent";
                break;
            case "Sent":
                newStatus = "Received";
                break;
            case "Received":
                newStatus = "Returned";
                break;
            default:
                newStatus = "Available";
                reservationService.deleteReservationByBookId(id);
                break;
        }

        System.out.println(newStatus);
        bookService.changeStatus(newStatus, id);
        /* */
    }

    @GetMapping("/borrowed_books/{id}")
    public List<BookEntity> getBorrowedBooks(@PathVariable String id) {
        Integer idAsInt = Integer.parseInt(id);
        List<ReservationEntity> reservationsByUser = reservationService.getAllReservationsByUser(idAsInt);
        //System.out.println(reservationsByUser.size());

        List<BookEntity> books = new ArrayList<>();
        for (ReservationEntity reservationEntity : reservationsByUser) {
            BookEntity entity = bookService.getBook(reservationEntity.getBookId());
            books.add(entity);
        }

        return books;
    }

    @PostMapping("/delete_book_borrowed")
    public void deleteBorrowedBook(@RequestBody StatusRequest request) {
        reservationService.deleteReservationByBookId(request.getId());
    }
}
