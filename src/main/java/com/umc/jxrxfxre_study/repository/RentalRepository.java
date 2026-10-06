package com.umc.jxrxfxre_study.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RentalRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll(){
        String sql = "SELECT * FROM rental";
        return jdbcTemplate.queryForList(sql);
    }

    public void saveRental(Map<String, Object> body) {
        String sql = "INSERT INTO rental (user_id, book_id, rented_at, due_at) VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY))";
        jdbcTemplate.update(
                sql,
                body.get("userId"),
                body.get("bookId")
        );
    }

    public void saveReturn (Long returnId) {
        String sql = "UPDATE rental SET returned_at = NOW() WHERE rental_id = ?";
        jdbcTemplate.update(sql, returnId);
    }

}
