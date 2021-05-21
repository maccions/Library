package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.Book;
import com.federico.library.exception.AuthorException;
import com.federico.library.exception.BookException;

public interface BookService {

	public Book addBook(Book book) throws BookException, AuthorException;
	public boolean removeBook(Book book) throws BookException;
	public List<Book> getAllBook();
	public Book getBookById(Long id) throws BookException;
	public Book getBook(Book book) throws BookException;
	public Book getBookByTitle(Book book) throws BookException;
	public List<Book> getAllBookByAuthor(Book book) throws AuthorException, BookException;
	public Book editBook(Book book) throws BookException, AuthorException;
}
