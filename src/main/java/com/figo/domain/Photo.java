package com.figo.domain;

import lombok.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@NonNull
public class Photo extends BaseDomain {
    List<String> urls;
    String carNumber;

    @Builder(builderMethodName = "childBuilder")
    public Photo(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                   @NonNull List<String> urls, @NonNull String carNumber  ){
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.urls=urls;
        this.carNumber=carNumber;

    }
}
