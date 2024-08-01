package com.devkobe.portfolioBlog.dto.create;

import com.devkobe.portfolioBlog.domain.Content;
import lombok.Getter;

@Getter
public class ContentResponse {

    private final String representativeImageUrl;
    private final String category;
    private final String title;
    private final String connectUrl;

    public ContentResponse(Content content) {
        this.representativeImageUrl = content.getRepresentativeImageUrl();
        this.category = content.getCategory();
        this.title = content.getTitle();
        this.connectUrl = content.getConnectUrl();
    }
}
