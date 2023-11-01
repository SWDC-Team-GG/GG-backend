package com.gagi.swdc.web.dto;

import com.gagi.swdc.domain.search.Search;
import com.gagi.swdc.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveSearchDto {
    private User userId;
    private String originSearch;
    private String afterSearch;
    private String mean;

//    public Search toEntity() {
//        return Search.builder()
//                .userId(userId)
//                .originSearch(originSearch)
//                .afterSearch(afterSearch)
//                .mean(mean)
//                .build();
//    }
}
