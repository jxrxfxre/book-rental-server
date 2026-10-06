package com.umc.jxrxfxre_study.controller;

import com.umc.jxrxfxre_study.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping
    public List<Map<String, Object>> getRentals() {
        return rentalService.getAllRentals();
    }

    @PostMapping
    public String createRental(@RequestBody Map<String, Object> body) {
        rentalService.createRental(body);
        return "대여 등록이 완료되었습니다!";
    }

    @PostMapping("/{rentalId}/return")
    public String createReturn(@PathVariable Long rentalId) {
        rentalService.createReturn(rentalId);
        return "반납이 완료되었습니다!";
    }

}
