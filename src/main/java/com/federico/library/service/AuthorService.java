package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.Author;
import com.federico.library.exception.AuthorException;

public interface AuthorService {

	/**
	 * Create a new author with the given one. Only name is needed.
	 * 
	 * @param author to be created
	 * @return Author created
	 * @throws AuthorException if that name already exist
	 */
	public Author addAuthor(Author author) throws AuthorException;

	/**
	 * Remove the author given. Only author id is needed. Throw AuthorException if
	 * there are book write by given author.
	 * 
	 * @param author to be removed
	 * @return true for successful delete
	 * @throws AuthorException if there is book write by that author.
	 */
	public boolean removeAuthor(Author author) throws AuthorException;

	/**
	 * Retrieve all author
	 * 
	 * @return all author
	 */
	public List<Author> getAllAuthor();

	/**
	 * Retrieve the given author, only author id is needed. Throw author exception for invalid id.
	 * 
	 * @param author
	 * @return
	 * @throws AuthorException
	 */
	public Author getAuthor(Author author) throws AuthorException;
	
	/**
	 * Retrieve the author identified by given id. Throw author exception for invalid id.
	 * 
	 * @param id to looking for
	 * @return author or null
	 * @throws AuthorException for invalid author
	 */
	public Author getAuthorById(Long id) throws AuthorException;
	
	/**
	 * Retrieve the author with the name in the given one. Throw AuthorException for missing parameter author or name.
	 * 
	 * @param author to search for name
	 * @return author or null
	 * @throws AuthorException for invalid name
	 */
	public Author getAuthorByName(Author author) throws AuthorException;

	/**
	 * Edit given author name. Throw AuthorException if no author is found with the given author id or name already exist
	 * @param author to edit
	 * @return author
	 * @throws AuthorException for author not found or invalid name
	 */
	Author editAuthor(Author author) throws AuthorException;

}
