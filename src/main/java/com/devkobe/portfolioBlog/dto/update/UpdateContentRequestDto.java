package com.devkobe.portfolioBlog.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateContentRequestDto {

    private String representativeImageUrl;
    private String category;
    private String title;
    private String connectUrl;
}
