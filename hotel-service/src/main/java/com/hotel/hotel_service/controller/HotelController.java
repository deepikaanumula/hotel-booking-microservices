package com.hotel.hotel_service.controller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hotel.hotel_service.entity.Hotel;
import com.hotel.hotel_service.entity.HotelRequest;
import com.hotel.hotel_service.service.HotelService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/hotels")
@Tag(name = "Hotel API")
public class HotelController {

    @Autowired
    private HotelService service;

    // CREATE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Hotel createHotel(@ModelAttribute HotelRequest request) throws IOException {

        Hotel hotel = request.toHotel();
        MultipartFile image = request.getImage();

        if (image != null && !image.isEmpty()) {

            // ✅ ADD THIS
            String cleanName = image.getOriginalFilename()
                    .replace(" ", "_");

            String fileName = UUID.randomUUID() + "_" + cleanName;

            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, image.getBytes());

            hotel.setCoverImageUrl("/uploads/" + fileName);
        }

        return service.create(hotel);
    }




    // READ ALL
    @GetMapping
    public List<Hotel> getAll() {
        List<Hotel> hotels = service.getAll();

        hotels.forEach(hotel -> {
            if (hotel.getCoverImageUrl() != null) {
                hotel.setCoverImageUrl(
                    "http://localhost:8083" + hotel.getCoverImageUrl()
                );
            }
        });

        return hotels;
    }


    // READ BY ID
    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Hotel update(@PathVariable Long id,
                        @RequestBody Hotel hotel) {
        return service.update(id, hotel);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

