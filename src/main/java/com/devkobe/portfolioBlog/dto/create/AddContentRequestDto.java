package com.devkobe.portfolioBlog.dto.create;

import com.devkobe.portfolioBlog.domain.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddContentRequestDto {

    private String representativeImageUrl;
    private String category;
    private String title;
    private String connectUrl;

    public Content toEntity() {
        return Content.builder()
            .representativeImageUrl(representativeImageUrl)
            .category(category)
            .title(title)
            .connectUrl(connectUrl)
            .build();
    }
}
