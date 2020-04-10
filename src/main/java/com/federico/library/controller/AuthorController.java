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
import com.federico.library.entity.Author;
import com.federico.library.exception.AuthorException;
import com.federico.library.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping(value = "/all")
	public List<Author> findAll() {
		return authorService.getAllAuthor();
	}

	@GetMapping
	public ResponseEntity<?> findAuthor(@RequestBody Author author) {
		try {
			return new ResponseEntity<>(authorService.getAuthor(author), HttpStatus.OK);
		} catch (AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAuthorById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
		} catch (AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/name")
	public ResponseEntity<?> findAuthorByName(@RequestBody Author author) {
		try {
			return new ResponseEntity<>(authorService.getAuthorByName(author), HttpStatus.OK);
		} catch (AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<?> createAuthor(@RequestBody Author author) {
		try {
			return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
		} catch (AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAuthor(@RequestBody Author author) {
		try {
			authorService.removeAuthor(author);
		} catch (AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<?> editAuthor(@RequestBody Author author) {
		try {
			return new ResponseEntity<>(authorService.editAuthor(author), HttpStatus.OK);
		} catch (AuthorException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.CONFLICT);
		}
	}

}
