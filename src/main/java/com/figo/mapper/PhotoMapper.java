package com.figo.mapper;

import com.figo.domain.Photo;
import com.figo.dtos.photos.PhotoDTO;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PhotoMapper implements BaseMapper<Photo, PhotoDTO, PhotoDTO, PhotoDTO> {
    @Override
    public Photo fromCreateDTO(@NonNull PhotoDTO dto) {
        return Photo.childBuilder().
                urls(dto.getUrls()).
                carNumber(dto.getCarNumber()).
                build();
    }

    @Override
    public Photo fromUpdateDTO(@NonNull PhotoDTO dto) {
        return Photo.childBuilder().
                urls(dto.getUrls()).
                carNumber(dto.getCarNumber()).
                build();
    }

    @Override
    public PhotoDTO toDTO(@NonNull Photo domain) {
        return PhotoDTO.childBuilder().
                urls(domain.getUrls()).
                carNumber(domain.getCarNumber()).
                build();
    }

    @Override
    public List<PhotoDTO> toDTOs(@NonNull List<Photo> domain) {
        List<PhotoDTO> photoDTOs = new ArrayList<>();
        for (Photo value : domain) {
            PhotoDTO photo = toDTO(value);
            photoDTOs.add(photo);
        }
        return photoDTOs;
    }
}
