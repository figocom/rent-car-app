package com.figo.dtos.cars;

import com.figo.dtos.base.GenericDTO;
import com.figo.enums.CarStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CarDTO extends GenericDTO {

    private String carModel;

    private String carNumber;

    private String countOfPlace;

    private String carColor;

    private String carRegion;

    private String carYear;

    private String pricePerDay;

    private String additionInfo;

    private String carStatus= String.valueOf(CarStatus.NOT_ON_RENT);

    @Builder(builderMethodName = "childBuilder")
    public CarDTO(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
               @NonNull String carModel, @NonNull String carNumber, @NonNull String carColor, String carStatus, @NonNull String carRegion,
               @NonNull String countOfPlace, @NonNull String carYear , @NonNull String pricePerDay , String additionInfo) {
        super(id);
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
