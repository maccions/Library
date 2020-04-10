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
import com.federico.library.entity.Copy;
import com.federico.library.exception.BookException;
import com.federico.library.exception.BookShelfException;
import com.federico.library.exception.CopyException;
import com.federico.library.service.CopyService;


@RestController
@RequestMapping("/copy")
public class CopyController {

	@Autowired
	private CopyService copyService;
	
	@PostMapping
	public ResponseEntity<?> createCopy(@RequestBody Copy copy) {
		try {
			return new ResponseEntity<>(copyService.addCopy(copy), HttpStatus.OK);
		} catch (BookShelfException | CopyException | BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteCopy(@RequestBody Copy copy) {
		try {
			copyService.removeCopy(copy);
		} catch (CopyException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/all")
	public List<Copy> findAll() {
		return copyService.getAllCopy();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findCopyById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(copyService.getCopyById(id), HttpStatus.OK);
		} catch (CopyException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> editCopy(@RequestBody Copy copy) {
		try {
			return new ResponseEntity<>(copyService.editCopy(copy), HttpStatus.OK);
		} catch ( CopyException | BookException | BookShelfException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> findCopy(@RequestBody Copy copy) {
		try {
			return new ResponseEntity<>(copyService.getCopy(copy), HttpStatus.OK);
		} catch (CopyException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/book")
	public ResponseEntity<?> findCopyByBook(@RequestBody Copy copy) {
		try {
			return new ResponseEntity<>(copyService.getCopyByBook(copy), HttpStatus.OK);
		} catch (CopyException | BookException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/bookShelf")
	public ResponseEntity<?> findCopyByBookshelf(@RequestBody Copy copy) {
		try {
			return new ResponseEntity<>(copyService.getCopyByBookShelf(copy), HttpStatus.OK);
		} catch (CopyException | BookShelfException e) {
			return new ResponseEntity<>(new ErrorDTO(e.getDescription(), e.getError()), HttpStatus.BAD_REQUEST);
		}
	}
	
}
