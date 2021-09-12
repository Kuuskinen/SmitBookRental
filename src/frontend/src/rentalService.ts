import axios from "axios";

export default {getBooks, getOwnerBooks, getOneBook, getBookReservation, getMyBorrowedBooks
}

function getBooks() {
    return axios
        .get('http://localhost:8080/allBooks')
        .catch(error => console.log(error))
}

function getOwnerBooks(id: number) {
    return axios
        .get('http://localhost:8080/ownerBooks/' + id)
        .catch(error => console.log(error))
}

function getOneBook(id: number) {
    return axios
        .get('http://localhost:8080/get_one_book/' + id)
        .catch(error => console.log(error))
}

function getBookReservation(id: number) {
    return axios
        .get('http://localhost:8080/reservations_of_book/' + id)
        .catch(error => console.log(error))
}

function getMyBorrowedBooks(id: number) {
    return axios
        .get('http://localhost:8080/borrowed_books/' + id)
        .catch(error => console.log(error))
}

