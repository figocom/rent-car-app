package com.figo.domain;

import lombok.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomain implements Entity {

    private String id;

    private boolean deleted;

    private LocalDateTime createdAt = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));

    private LocalDateTime updatedAt =LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));;

    private String createdBy;

    private String updatedBy;

    public BaseDomain(String id) {
        this.id=id;
    }
}
