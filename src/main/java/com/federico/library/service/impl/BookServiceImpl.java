package com.federico.library.service.impl;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.federico.library.entity.Author;
import com.federico.library.entity.Book;
import com.federico.library.enumeration.ErrorType;
import com.federico.library.exception.AuthorException;
import com.federico.library.exception.BookException;
import com.federico.library.repository.BookRepository;
import com.federico.library.service.AuthorService;
import com.federico.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorService authorService;

	@Override
	public Book addBook(Book book) throws BookException, AuthorException {
		if(book == null) {
			throw new BookException("Missing data", ErrorType.MISSING_PARAMETER);
		}
		if (Strings.isEmpty(book.getTitle())) {
			throw new BookException("Missing title", ErrorType.MISSING_PARAMETER);
		}
		Author author = book.getAuthor();
		author = authorService.getAuthor(author);
		book.setAuthor(author);
		book.setTitle(null);
		return bookRepository.save(book);
	}

	@Override
	public boolean removeBook(Book book) throws BookException {
		try {
			bookRepository.deleteById(book.getId());
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new BookException("Invalid book", ErrorType.MISSING_PARAMETER);
		}
		return true;
	}

	@Override
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) throws BookException {
		if (id == null) {
			throw new BookException("Missing id", ErrorType.MISSING_PARAMETER);
		}
		return bookRepository.findById(id).get();
	}
	
	@Override
	public Book getBook(Book book) throws BookException {
		if (book == null) {
			throw new BookException("Missing book", ErrorType.MISSING_PARAMETER);
		}
		return getBookById(book.getId());
	}

	@Override
	public Book getBookByTitle(Book book) throws BookException {
		if (book == null || Strings.isEmpty(book.getTitle())) {
			throw new BookException("Missing data", ErrorType.MISSING_PARAMETER);
		}
		return bookRepository.findByTitle(book.getTitle());
	}

	@Override
	public List<Book> getAllBookByAuthor(Book book) throws AuthorException, BookException {
		if(book == null || book.getAuthor() == null ) {
			throw new BookException("Missing parameter", ErrorType.MISSING_PARAMETER);			
		}
		Author author = authorService.getAuthorById(book.getAuthor().getId());
		if (author == null) {
			throw new AuthorException("Invalid author", ErrorType.INVALID_PARAMETER);
		}
		return bookRepository.findAllByAuthor(author);
	}

	@Override
	public Book editBook(Book book) throws BookException, AuthorException {
		if (book == null) {
			throw new BookException("Missing book to edit", ErrorType.MISSING_PARAMETER);
		}
		Book oldBook = getBookById(book.getId());
		if (oldBook == null) {
			throw new BookException("Book not found", ErrorType.INVALID_PARAMETER);
		}
		if (Strings.isEmpty(book.getTitle())) {
			book.setTitle(oldBook.getTitle());
		} else {
			Book temp = getBookByTitle(book);
			if ( temp != null && temp.getId() != oldBook.getId()) {
				throw new BookException("Title already exist", ErrorType.INVALID_PARAMETER);
			}
		}
		if(book.getAuthor() == null) {
			book.setAuthor(oldBook.getAuthor());
		} else {
			Author a = authorService.getAuthor(book.getAuthor());
			if(a == null) {
				throw new AuthorException("Author not found", ErrorType.INVALID_PARAMETER);
			}
			book.setAuthor(a);
		}
		return bookRepository.save(book);
	}

}