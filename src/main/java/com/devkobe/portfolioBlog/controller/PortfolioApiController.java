package com.devkobe.portfolioBlog.controller;

import com.devkobe.portfolioBlog.domain.Content;
import com.devkobe.portfolioBlog.dto.create.AddContentRequestDto;
import com.devkobe.portfolioBlog.dto.create.ContentResponseDto;
import com.devkobe.portfolioBlog.service.PortfolioBlogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/api/contents")
    public ResponseEntity<List<ContentResponseDto>> findAllContents() {
        List<ContentResponseDto> contents = portfolioBlogService.findAll()
                                                                .stream()
                                                                .map(ContentResponseDto::new)
                                                                .toList();

        return ResponseEntity.ok()
                             .body(contents);
    }

    @GetMapping("/api/contents/{id}")
    public ResponseEntity<ContentResponseDto> findArticle(@PathVariable("id") Long id) {
        Content content = portfolioBlogService.findById(id);

        return ResponseEntity.ok()
                             .body(new ContentResponseDto(content));
    }
}
