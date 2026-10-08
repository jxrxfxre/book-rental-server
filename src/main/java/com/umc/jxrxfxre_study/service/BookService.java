package com.umc.jxrxfxre_study.service;

import com.umc.jxrxfxre_study.dto.BookResponse;
import com.umc.jxrxfxre_study.dto.CreateBookRequest;
import com.umc.jxrxfxre_study.entity.Book;
import com.umc.jxrxfxre_study.entity.Category;
import com.umc.jxrxfxre_study.repository.BookRepository;
import com.umc.jxrxfxre_study.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<BookResponse> getBooks() {
        return bookRepository.findAllByOrderByBookIdDesc().stream()
                .map(BookResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByKeyword(String titleContaining) {
        return bookRepository.findByTitleContainingOrderByBookIdDesc(titleContaining).stream()
                .map(BookResponse::from).toList();
    }

    @Transactional
    public BookResponse createBook(CreateBookRequest request) {
        if (bookRepository.existsByTitle(request.title())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 도서 제목입니다.");
        }
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        Book book = new Book(category, request.title(), request.description());
        return BookResponse.from(bookRepository.save(book));
    }

}
