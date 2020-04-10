package com.federico.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.federico.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByName(String name);

}
