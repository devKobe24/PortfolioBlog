package com.devkobe.portfolioBlog.dto.create;

import com.devkobe.portfolioBlog.domain.Content;
import com.devkobe.portfolioBlog.dto.create.ContentResponseDto.CategoryType;
import lombok.Getter;

@Getter
public class ContentResponseDto {

    private final String representativeImageUrl;
    private final CategoryType category;
    private final String title;
    private final String connectUrl;

    public ContentResponseDto(Content content) {
        this.representativeImageUrl = content.getRepresentativeImageUrl();
        this.category = getCategoryType(content.getCategory());
        this.title = content.getTitle();
        this.connectUrl = content.getConnectUrl();
    }

    public enum CategoryType {
        DEFAULT,
        IOS,
        ANDROID,
        FRONTEND,
        BACKEND;
    }

    public static CategoryType getCategoryType(String dbValue) {
        for (CategoryType type : CategoryType.values()) {
            if (type.name().equals(dbValue)) {
                return type;
            }
        }
        return CategoryType.DEFAULT;
    }
}




