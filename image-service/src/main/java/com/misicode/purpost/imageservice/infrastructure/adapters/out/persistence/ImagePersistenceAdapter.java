package com.misicode.purpost.imageservice.infrastructure.adapters.out.persistence;

import com.misicode.purpost.imageservice.application.ports.out.ImagePersistencePort;
import com.misicode.purpost.imageservice.domain.model.Image;
import com.misicode.purpost.imageservice.infrastructure.adapters.out.persistence.mappers.ImagePersistenceMapper;
import com.misicode.purpost.imageservice.infrastructure.adapters.out.persistence.repositories.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImagePersistenceAdapter implements ImagePersistencePort {
    private final ImageRepository imageRepository;

    public ImagePersistenceAdapter(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Image> findById(String id) {
        return imageRepository.findById(id).map(ImagePersistenceMapper::toImage);
    }

    @Override
    public Image save(Image image) {
        return ImagePersistenceMapper.toImage(
                imageRepository.save(ImagePersistenceMapper.toImageEntity(image))
        );
    }
}
