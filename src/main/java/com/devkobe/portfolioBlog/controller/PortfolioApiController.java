package com.devkobe.portfolioBlog.controller;

import com.devkobe.portfolioBlog.domain.Content;
import com.devkobe.portfolioBlog.dto.create.AddContentRequestDto;
import com.devkobe.portfolioBlog.service.PortfolioBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PortfolioApiController {

    private final PortfolioBlogService portfolioBlogService;

    @PostMapping("/api/contents")
    public ResponseEntity<Content> addContent(@RequestBody AddContentRequestDto requestDto) {
        Content savedContent = portfolioBlogService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(savedContent);
    }
}
