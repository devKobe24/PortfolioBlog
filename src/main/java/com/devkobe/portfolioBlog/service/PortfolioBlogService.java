package com.devkobe.portfolioBlog.service;

import com.devkobe.portfolioBlog.domain.Content;
import com.devkobe.portfolioBlog.dto.create.AddContentRequestDto;
import com.devkobe.portfolioBlog.repository.PortfolioBlogRepository;
import java.util.List;
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

    // DB에 저장되어 있는 컨텐츠을 모두 가져오는 메서드
    public List<Content> findAll() {
        return portfolioBlogRepository.findAll();
    }

    // 컨텐츠 하나를 가져오는 메서드
    public Content fincById(Long id) {
        return portfolioBlogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Not found =======>>>>>> " + id));
    }
}
