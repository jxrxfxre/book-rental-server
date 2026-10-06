package com.umc.jxrxfxre_study.repository;

import com.umc.jxrxfxre_study.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByBookIdDesc();
}
