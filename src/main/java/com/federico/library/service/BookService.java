package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.Book;
import com.federico.library.exception.AuthorException;
import com.federico.library.exception.BookException;

public interface BookService {

	/**
	 * Create a new book book by the given one, only need book title and the author
	 * id. Throw BookException for missing book title or title already exist. Throw
	 * authorException if no author found with given id.
	 * 
	 * @param book to be created
	 * @return book created
	 * @throws BookException   for invalid title
	 * @throws AuthorException for invalid author
	 */
	public Book addBook(Book book) throws BookException, AuthorException;

	/**
	 * Remove the book given, only book id is needed. Throw BookException for
	 * invalid id.
	 * 
	 * @param book to be removed
	 * @return true
	 * @throws BookException for invalid book.
	 */
	public boolean removeBook(Book book) throws BookException;

	/**
	 * Retrieve all book.
	 * 
	 * @return list of book
	 */
	public List<Book> getAllBook();

	/**
	 * Retrieves book with the given id or null if no book is found. Throw
	 * BookException for invalid book id.
	 * 
	 * @param id to looking for
	 * @return book or null
	 * @throws BookException
	 */
	public Book getBookById(Long id) throws BookException;

	/**
	 * Retrieves book given or null if no book is found, only book id is needed.
	 * Throw BookException for missing book or invalid book id.
	 * 
	 * @param book to looking for
	 * @return book or null
	 * @throws BookException
	 */
	public Book getBook(Book book) throws BookException;

	/**
	 * Retrieves the book with the given title or null if no book is find. Throw
	 * BookException for missing parameter or invalid title.
	 * 
	 * @param book to search for
	 * @return book or null
	 * @throws BookException for invalid book or title
	 */
	public Book getBookByTitle(Book book) throws BookException;

	/**
	 * Retrieves all book write by the author given, only author id needed into book. Throw AuthorException for invalid author.
	 * 
	 * @param athor to search for
	 * @return all book write by the given author
	 * @throws AuthorException for invalid author
	 * @throws BookException for missing book or author
	 */
	public List<Book> getAllBookByAuthor(Book book) throws AuthorException, BookException;

	/**
	 * Edit the given book, the book to edit must have an id. If there isn't some
	 * attribute let it unchanged. To change the author only the new author id is
	 * required. Throw BookException if there isn't book in repository or a book
	 * with the same title already exist. Throw AuthorException if no author is
	 * found with that id.
	 * 
	 * @param book to edit
	 * @return book edited
	 * @throws BookException   if no original book found or duplicated title
	 * @throws AuthorException if no author is found
	 */
	public Book editBook(Book book) throws BookException, AuthorException;
}
