package com.hotel.hotel_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotel_service.entity.Hotel;
import com.hotel.hotel_service.repo.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repo;

    public Hotel create(Hotel hotel) {
        return repo.save(hotel);
    }

    public List<Hotel> getAll() {
        return repo.findAll();
    }

    public Hotel getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    // UPDATE
    public Hotel update(Long id, Hotel updatedHotel) {
        Hotel existing = getById(id);

        existing.setName(updatedHotel.getName());
        existing.setLocation(updatedHotel.getLocation());
        existing.setDescription(updatedHotel.getDescription());
        existing.setPricePerNight(updatedHotel.getPricePerNight());

        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Hotel not found");
        }
        repo.deleteById(id);
    }
}
