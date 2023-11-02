package com.gagi.swdc.domain.search;

import com.gagi.swdc.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String originSearch;

    @Column
    private String afterSearch;

    @Column
    private String mean;

    @Builder
    public Search(Long userId, String originSearch, String afterSearch, String mean) {
        this.userId = userId;
        this.originSearch = originSearch;
        this.afterSearch = afterSearch;
        this.mean = mean;
    }
}
