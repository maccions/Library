package com.federico.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.federico.library.entity.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

}
