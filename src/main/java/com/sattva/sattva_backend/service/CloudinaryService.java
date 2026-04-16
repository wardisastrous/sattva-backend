package com.sattva.sattva_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    // Upload image to Cloudinary and return the public URL
    public String uploadImage(MultipartFile file) throws IOException {
        // Upload the file bytes to Cloudinary
        Map result = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap(
                "folder", "sattva-events",  // Organizes images in a folder
                "resource_type", "image"
            )
        );

        // Cloudinary returns the public URL as "secure_url"
        return (String) result.get("secure_url");
        // Returns something like:
        // https://res.cloudinary.com/yourcloud/image/upload/sattva-events/abc123.jpg
    }
}
