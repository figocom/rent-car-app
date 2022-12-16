package com.figo.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayCardCriteria extends GenericCriteria{
    Integer userId;
}
