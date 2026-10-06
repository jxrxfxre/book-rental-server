package com.umc.jxrxfxre_study.service;

import com.umc.jxrxfxre_study.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public List<Map<String, Object>> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void createRental(Map<String, Object> body) {
        rentalRepository.saveRental(body);
    }

    public void createReturn(Long rentalId) {
        rentalRepository.saveReturn(rentalId);
    }

}
