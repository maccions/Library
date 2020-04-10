package com.federico.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.federico.library.entity.Book;
import com.federico.library.entity.BookShelf;
import com.federico.library.entity.Copy;
import com.federico.library.enumeration.ErrorType;
import com.federico.library.exception.BookException;
import com.federico.library.exception.BookShelfException;
import com.federico.library.exception.CopyException;
import com.federico.library.repository.CopyRepository;
import com.federico.library.service.BookService;
import com.federico.library.service.BookShelfService;
import com.federico.library.service.CopyService;

public class CopyServiceImpl implements CopyService {

	@Autowired
	private CopyRepository copyRepository;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookShelfService bookShelfService;

	@Override
	public Copy addCopy(Copy copy) throws BookShelfException, CopyException, BookException {
		if (copy.getBook() == null || copy.getBookShelf() == null) {
			throw new CopyException("Missing parameters", ErrorType.MISSING_PARAMETER);
		}
		Book b = bookService.getBook(copy.getBook());
		BookShelf s = bookShelfService.getBookShelf(copy.getBookShelf());
		if (b == null || s == null) {
			throw new CopyException("Book or bookshelf not found", ErrorType.INVALID_PARAMETER);
		}
		copy.setBook(b);
		copy.setBookShelf(s);
		copy.setId(null);
		return copyRepository.save(copy);
	}

	@Override
	public boolean removeCopy(Copy copy) throws CopyException {
		copy = getCopy(copy);
		copyRepository.delete(copy);
		return true;
	}

	@Override
	public Copy editCopy(Copy copy) throws BookException, BookShelfException, CopyException {
		Copy oldCopy = getCopy(copy);
		if (oldCopy == null) {
			throw new CopyException("Copy not found", ErrorType.INVALID_PARAMETER);
		}
		if (copy.getBook() != null) {
			Book book = bookService.getBook(copy.getBook());
			oldCopy.setBook(book);
		}
		if (copy.getBookShelf() != null) {
			BookShelf bookShelf = bookShelfService.getBookShelf(copy.getBookShelf());
			oldCopy.setBookShelf(bookShelf);
		}
		return copyRepository.save(oldCopy);
	}

	@Override
	public List<Copy> getAllCopy() {
		return copyRepository.findAll();
	}

	@Override
	public Copy getCopy(Copy copy) throws CopyException {
		if (copy == null) {
			throw new CopyException("Missing parameter", ErrorType.MISSING_PARAMETER);
		}
		return getCopyById(copy.getId());
	}

	@Override
	public Copy getCopyById(Long copyId) throws CopyException {
		if (copyId == null) {
			throw new CopyException("Missing parameter", ErrorType.MISSING_PARAMETER);
		}
		return copyRepository.findById(copyId).orElse(null);
	}

	@Override
	public List<Copy> getCopyByBook(Copy copy) throws BookException, CopyException {
		if(copy == null) {
			throw new CopyException("Missing data", ErrorType.MISSING_PARAMETER);
		}
		Book book = bookService.getBook(copy.getBook());
		if(book == null) {
			throw new CopyException("No book found", ErrorType.INVALID_PARAMETER);
		}
		return copyRepository.findAll(Example.of(Copy.builder().book(book).build()));
	}

	@Override
	public List<Copy> getCopyByBookShelf(Copy copy) throws CopyException, BookShelfException {
		if(copy == null) {
			throw new CopyException("Missing data", ErrorType.MISSING_PARAMETER);
		}
		BookShelf bookShelf = bookShelfService.getBookShelf(copy.getBookShelf());
		if(bookShelf == null) {
			throw new CopyException("No book shelf found", ErrorType.INVALID_PARAMETER);
		}
		return copyRepository.findAll(Example.of(Copy.builder().bookShelf(bookShelf).build()));
	}

}
