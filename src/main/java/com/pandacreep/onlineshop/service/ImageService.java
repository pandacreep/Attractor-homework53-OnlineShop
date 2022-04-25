package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.model.Image;
import com.pandacreep.onlineshop.repo.ImagesRepository;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImagesRepository imagesRepository;

    public Image addImage(MultipartFile file) {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.length == 0) {
            return null;
        }

        Binary bData = new Binary(data);
        Image image = Image.builder().image(bData).build();

        imagesRepository.save(image);
        return image;
    }

    public Image getById(String imageId) {
        Image image = imagesRepository.findById(imageId).get();
        return image;
    }
}
