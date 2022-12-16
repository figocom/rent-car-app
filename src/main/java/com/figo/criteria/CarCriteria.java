package com.figo.criteria;

import com.figo.enums.CarStatus;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarCriteria extends GenericCriteria {
    CarStatus status;
}
