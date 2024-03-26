package com.gagi.swdc.domain.mean.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Entity
public class Mean {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private String mean;

    public Mean(String word, String mean) {
        this.word = word;
        this.mean = mean;
    }
}
