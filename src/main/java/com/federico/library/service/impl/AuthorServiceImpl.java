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
import com.federico.library.repository.AuthorRepository;
import com.federico.library.service.AuthorService;
import com.federico.library.service.BookService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookService bookService;

	@Override
	public Author addAuthor(Author author) throws AuthorException {
		if (author == null || Strings.isBlank(author.getName()))
			throw new AuthorException("Invalid name", ErrorType.MISSING_PARAMETER);
		author.setId(null);
		return authorRepository.save(author);
	}

	@Override
	public boolean removeAuthor(Author author) throws AuthorException {
		Author a = getAuthor(author);
		if (a == null) {
			throw new AuthorException("Author not found", ErrorType.INVALID_PARAMETER);
		}
		boolean test = true; 
		try {
			test = !bookService.getAllBookByAuthor(Book.builder().author(author).build()).isEmpty();
		} catch (BookException e) {
			//impossible, author retrieved directly from repository
		}
		if(test) {
			throw new AuthorException("There are some book write by author " + author.getId(),
					ErrorType.INVALID_OPERATION);
		}
		authorRepository.delete(a);
		return true;
	}

	@Override
	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}

	@Override
	public Author getAuthorByName(Author author) throws AuthorException {
		if (author == null || Strings.isBlank(author.getName())) {
			throw new AuthorException("Missing name", ErrorType.MISSING_PARAMETER);
		}
		return authorRepository.findByName(author.getName());
	}

	@Override
	public Author getAuthor(Author author) throws AuthorException {
		if (author == null) {
			throw new AuthorException("Missing data", ErrorType.MISSING_PARAMETER);
		}
		return getAuthorById(author.getId());
	}

	@Override
	public Author getAuthorById(Long id) throws AuthorException {
		if (id == null) {
			throw new AuthorException("Missing id", ErrorType.MISSING_PARAMETER);
		}
		return authorRepository.findById(id).orElse(null);
	}
	
	@Override
	public Author editAuthor(Author author) throws AuthorException{
		if(getAuthor(author) == null) {
			throw new AuthorException("Missing author", ErrorType.INVALID_PARAMETER);
		}
		if(Strings.isEmpty(author.getName()) || getAuthorByName(author) != null) {
			throw new AuthorException("Invalid name", ErrorType.INVALID_PARAMETER);
		}
		return authorRepository.save(author);
	}

}
