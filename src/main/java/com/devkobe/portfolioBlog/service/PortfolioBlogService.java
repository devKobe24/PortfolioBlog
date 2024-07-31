package com.devkobe.portfolioBlog.service;

import com.devkobe.portfolioBlog.domain.Content;
import com.devkobe.portfolioBlog.dto.create.AddContentRequestDto;
import com.devkobe.portfolioBlog.repository.PortfolioBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortfolioBlogService {

    private final PortfolioBlogRepository portfolioBlogRepository;

    // 포트폴리오 컨텐츠 추가 메서드
    public Content save(AddContentRequestDto requestDto) {
        return portfolioBlogRepository.save(requestDto.toEntity());
    }
}
