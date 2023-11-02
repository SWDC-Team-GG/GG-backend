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
    private String meaning;

    @Column
    private String part;

    @Column
    private String plainWord;

    @Column
    private String translateWord;

    @Column
    private String translateWordLevel;

    @Builder
    public Search(Long userId, String meaning, String part, String plainWord, String translateWord, String translateWordLevel) {
        this.userId = userId;
        this.meaning = meaning;
        this.part = part;
        this.plainWord = plainWord;
        this.translateWord = translateWord;
        this.translateWordLevel = translateWordLevel;
    }
}
