package com.federico.library.service;

import java.util.List;

import com.federico.library.entity.Author;
import com.federico.library.exception.AuthorException;

public interface AuthorService {

	public Author addAuthor(Author author) throws AuthorException;
	public boolean removeAuthor(Author author) throws AuthorException;
	public List<Author> getAllAuthor();
	public Author getAuthor(Author author) throws AuthorException;
	public Author getAuthorById(Long id) throws AuthorException;
	public Author getAuthorByName(Author author) throws AuthorException;
	public Author editAuthor(Author author) throws AuthorException;

}
