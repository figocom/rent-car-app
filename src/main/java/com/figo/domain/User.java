package com.figo.domain;

import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@ToString
@NonNull
public class User extends BaseDomain implements Entity {
    String firstName;
    String lastName;
    String phoneNumber;
    String username;
    String region;
    String address;
    String password;
    boolean isAdmin;


    @Builder(builderMethodName = "childBuilder")
    public User(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                @NonNull String firstName , @NonNull String lastName ,  @NonNull String phoneNumber , @NonNull String username,
                @NonNull String region, @NonNull String address, String password , boolean isAdmin){
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.username=username;
        this.region=region;
        this.address=address;
        this.password=password;
        this.isAdmin=isAdmin;
    }


    public User(String id, String first_name, String last_name, String phone_number, String username, String region, String address, String password, boolean isAdmin) {
        super(id);
        this.firstName=first_name;
        this.lastName=last_name;
        this.phoneNumber=phone_number;
        this.username= username;
        this.region=region;
        this.address=address;
        this.password=password;
        this.isAdmin=isAdmin;
    }


}
