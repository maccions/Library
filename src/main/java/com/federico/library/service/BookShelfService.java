package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.BookShelf;
import com.federico.library.exception.BookShelfException;

public interface BookShelfService {

	/**
	 * Add given book shelf to repository. throw BookShelfException for missing book
	 * shelf data or book shelf with same data exist. ignore any given id.
	 * 
	 * @param bookShelf to add
	 * @return bookshelf created
	 * @throws BookShelfException for invalid parameter
	 */
	public BookShelf addBookShelf(BookShelf bookShelf) throws BookShelfException;

	/**
	 * Search bookShelf shelf with the given id, return target book shelf or null.
	 * throw BookShelfException for null id
	 * 
	 * @param bookShelfId to looking for
	 * @return bookShelf or null
	 * @throws BookShelfException for invalid or missing parameter
	 */
	public BookShelf getBookShelfById(Long bookShelfId) throws BookShelfException;
	
	/**
	 * find a book shelf in repository, only book shelf id is needed. return target book shelf
	 * or null. throw BookShelfException for missing bookShelf or missing id.
	 * 
	 * @param bookShelf to looking for
	 * @return bookShelf or null
	 * @throws BookShelfException for missing or invalid parameter
	 */
	public BookShelf getBookShelf(BookShelf bookShelf) throws BookShelfException;
	
	/**
	 * get all bookshelf
	 * 
	 * @return list of bookshelf
	 */
	public List<BookShelf> getAllBookShelf();

	/**
	 * Edit a book shelf with the given data, update only data found in given book
	 * shelf. throw BookShelfException for missing or invalid parameter given.
	 * 
	 * @param bookShelf to be edited
	 * @return bookshelf edited
	 * @throws BookShelfException for invalid or missing parameter
	 */
	public BookShelf editBookShelf(BookShelf bookShelf) throws BookShelfException;
	
	/**
	 * Remove book shelf given, only book shelf Id is needed. Throw
	 * BookShelfException for invalid id or it book shelf isn't empty.
	 * 
	 * @param bookShelf to be removed
	 * @return true
	 * @throws BookShelfException for invalid parameters or not empty bookshelf
	 */
	public boolean removeBookShelf(BookShelf bookShelf) throws BookShelfException;

}
