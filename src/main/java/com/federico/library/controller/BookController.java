package com.federico.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.federico.library.dto.ErrorDTO;
import com.federico.library.entity.Book;
import com.federico.library.exception.AuthorException;
import com.federico.library.exception.BookException;
import com.federico.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<?> createBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
		} catch (BookException | AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteBook(@RequestBody Book book) {
		try {
			bookService.removeBook(book);
		} catch (BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/all")
	public List<Book> findAll() {
		return bookService.getAllBook();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findBookById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
		} catch (BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> findBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.getBook(book), HttpStatus.OK);
		} catch (BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/title")
	public ResponseEntity<?> findBookByTitle(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.getBookByTitle(book), HttpStatus.OK);
		} catch (BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/author")
	public ResponseEntity<?> findBookByAuthor(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.getAllBookByAuthor(book), HttpStatus.OK);
		} catch (AuthorException | BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> editBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.editBook(book), HttpStatus.OK);
		} catch (BookException | AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
}
