package com.figo.dtos.photos;

import com.figo.dtos.base.DTO;
import com.figo.dtos.base.GenericDTO;
import lombok.*;


import javax.servlet.http.HttpSession;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PhotoDTO extends GenericDTO  {
    private List<String> urls;
    private String carNumber;

    @Builder(builderMethodName = "childBuilder")
    public PhotoDTO(String id, @NonNull List<String> urls, @NonNull String carNumber) {
        super(id);
       this.urls=urls;
       this.carNumber=carNumber;

    }
}
