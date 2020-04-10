package com.federico.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.federico.library.entity.Author;
import com.federico.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByTitle(String name);

	List<Book> findAllByAuthor(Author author);

}
