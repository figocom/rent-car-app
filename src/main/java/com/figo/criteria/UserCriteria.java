package com.figo.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserCriteria extends GenericCriteria{
    boolean isAdmin;
}
