package com.federico.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.federico.library.entity.BookShelf;
import com.federico.library.exception.BookShelfException;
import com.federico.library.service.BookShelfService;

@RestController
@RequestMapping("/bookShelf")
public class BookShelfController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BookShelfService bookShelfService;
	
	@PostMapping
	public ResponseEntity<?> createBookShelf(@RequestBody BookShelf bookShelf) {
		log.info(bookShelf.toString());
		try {
			bookShelf = bookShelfService.addBookShelf(bookShelf);
			log.info("bookshelf added: {}", bookShelf);
			return new ResponseEntity<>(bookShelf, HttpStatus.OK);
		} catch (BookShelfException e) {
			log.warn("{} - {} - {}", e.getError(), e.getDescription(), bookShelf);
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteBookShelf(@RequestBody BookShelf bookShelf) {
		try {
			bookShelfService.removeBookShelf(bookShelf);
		} catch (BookShelfException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/all")
	public List<BookShelf> findAll() {
		return bookShelfService.getAllBookShelf();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findBookShelfById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(bookShelfService.getBookShelfById(id), HttpStatus.OK);
		} catch (BookShelfException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> findBookShelf(@RequestBody BookShelf bookShelf) {
		try {
			return new ResponseEntity<>(bookShelfService.getBookShelf(bookShelf), HttpStatus.OK);
		} catch ( BookShelfException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> editBookShelf(@RequestBody BookShelf bookShelf) {
		try {
			return new ResponseEntity<>(bookShelfService.editBookShelf(bookShelf), HttpStatus.OK);
		} catch ( BookShelfException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
}
