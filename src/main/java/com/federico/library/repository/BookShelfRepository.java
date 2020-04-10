package com.federico.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.federico.library.entity.BookShelf;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

}