package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.Copy;
import com.federico.library.exception.BookException;
import com.federico.library.exception.BookShelfException;
import com.federico.library.exception.CopyException;

public interface CopyService {

	/**
	 * Add new copy to repository, need only Book and BookShelf id. For Book or
	 * Bookshelf missing correspondence throw CopyException, for missing BookShelfId
	 * or BookId throw the respective Exception.
	 * 
	 * @param copy to be added
	 * @return the new added Copy
	 * @throws CopyException      for missing parameter or not found parameter
	 *                            correspondence
	 * @throws BookException      for missing bookId
	 * @throws BookShelfException for missing bookShelfId
	 */
	public Copy addCopy(Copy copy) throws BookShelfException, CopyException, BookException;

	/**
	 * Remove the copy given, only copyId is needed. Throw CopyException for invalid
	 * id.
	 * 
	 * @param copy to remove
	 * @return true for operation success
	 * @throws CopyException for invalid Copy
	 */
	public boolean removeCopy(Copy copy) throws CopyException;

	/**
	 * Edit a copy with the given data, update only data found in given copy. throw
	 * CopyException for missing or invalid copy given, BookException for invalid
	 * book data, BookShelfException for invalid bookshelf data
	 * 
	 * @param copy to be edited
	 * @return copy with new data
	 * @throws CopyException      for any given data not found on repository
	 * @throws BookException      for invalid or missing book
	 * @throws BookShelfException for invalid or missing book shelf
	 */
	public Copy editCopy(Copy copy) throws CopyException, BookException, BookShelfException;

	/**
	 * get all copy in repository
	 * 
	 * @return a list with all copy
	 */
	public List<Copy> getAllCopy();

	/**
	 * find a copy in repository, only copy id is needed. throw CopyException for
	 * missing or invalid parameter
	 * 
	 * @param copy to search for
	 * @return a copy or null
	 * @throws CopyException for missing data
	 */
	public Copy getCopy(Copy copy) throws CopyException;

	/**
	 * Search copy with the given id, return target copy or null. throw
	 * CopyException for missing parameter.
	 * 
	 * @param copyId to looking for
	 * @return a copy or null
	 * @throws CopyException for missing data
	 */
	public Copy getCopyById(Long copyId) throws CopyException;

	/**
	 * Get all copy of a book, only book id needed. return target copy or null.
	 * throw copy exception for missing data or no book found, BookException for invalid book
	 * data.
	 * 
	 * @param copy to search by book
	 * @return a list of copy of given book
	 * @throws BookException for invalid book data
	 * @throws CopyException for missing data, or invalid book
	 */
	public List<Copy> getCopyByBook(Copy copy) throws BookException, CopyException;

	/**
	 * retrieve all copy in a book shelf, only bookshelf id needed. throw copy
	 * exception for missing data or no found book shelf, BookShelfException for invalid book shelf
	 * data.
	 * 
	 * @param copy
	 * @return a list of copy
	 * @throws BookShelfException for invalid book shelf data
	 * @throws CopyException for missing data, or invalid book shelf
	 */
	public List<Copy> getCopyByBookShelf(Copy copy) throws BookShelfException, CopyException;

}
