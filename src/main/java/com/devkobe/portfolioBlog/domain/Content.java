package com.devkobe.portfolioBlog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "representativeImage", nullable = false)
    private String representativeImage;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "connectUrl", nullable = false)
    private String connectUrl;

    @Builder
    public Content(String representativeImage, String category, String title, String connectUrl) {
        this.representativeImage = representativeImage;
        this.category = category;
        this.title = title;
        this.connectUrl = connectUrl;
    }

}
