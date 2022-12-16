package com.figo.domain;

import com.figo.enums.CarStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car extends BaseDomain {
    @NonNull
    private String carModel;
    @NonNull
    private String carNumber;
    @NonNull
    private Integer countOfPlace;
    @NonNull
    private String carColor;
    @NonNull
    private String carRegion;
    @NonNull
    private Integer carYear;
    @NonNull
    private String pricePerDay;

    private String additionInfo;

    private CarStatus carStatus=CarStatus.NOT_ON_RENT;

    @Builder(builderMethodName = "childBuilder")
    public Car(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
               @NonNull String carModel, @NonNull String carNumber, @NonNull String carColor, CarStatus carStatus, @NonNull String carRegion,
               @NonNull Integer countOfPlace, @NonNull Integer carYear , @NonNull String pricePerDay , String additionInfo) {
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.carColor = carColor;
        this.carStatus = carStatus;
        this.carRegion = carRegion;
        this.countOfPlace = countOfPlace;
        this.carYear = carYear;
        this.pricePerDay=pricePerDay;
        this.additionInfo=additionInfo;
    }
}
