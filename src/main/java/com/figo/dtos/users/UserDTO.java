package com.figo.rentcar.dtos.users;

import com.figo.rentcar.dtos.base.GenericDTO;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends GenericDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String region;
    private String address;
    private String password;
    private boolean isAdmin;
    private boolean deleted;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;


    @Builder(builderMethodName = "childBuilder")
    public UserDTO(String id, String firstName, String lastName, String username, String password, String phoneNumber, String region, String address, String createdAt, String updatedAt, String createdBy, String updatedBy, boolean isAdmin, boolean deleted) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.region = region;
        this.address = address;
        this.isAdmin = isAdmin;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;

    }
}
