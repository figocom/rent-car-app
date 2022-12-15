package com.figo.rentcar.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@NonNull
public class Photo extends BaseDomain {
    List<String> urls;
    Integer carId;
    @Builder(builderMethodName = "childBuilder")
    public Photo(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                   @NonNull List<String> urls, @NonNull Integer carId){
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.urls=urls;
        this.carId=carId;
    }
}
