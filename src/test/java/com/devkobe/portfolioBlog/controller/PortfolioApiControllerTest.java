package com.devkobe.portfolioBlog.controller;

import com.devkobe.portfolioBlog.domain.Content;
import com.devkobe.portfolioBlog.dto.create.AddContentRequestDto;
import com.devkobe.portfolioBlog.dto.update.UpdateContentRequestDto;
import com.devkobe.portfolioBlog.repository.PortfolioBlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PortfolioBlogRepository portfolioBlogRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                      .build();
        portfolioBlogRepository.deleteAll();
    }

    @DisplayName("addContent: 포트폴리오 블로그 콘텐츠 추가에 성공한다.")
    @Test
    public void addContent() throws Exception {
        // given
        final String url = "/api/contents";
        final String representativeImageUrl = "https://www.example.com/image-1.png";
        final String category = "BACKEND";
        final String title = "포트폴리오 블로그";
        final String connectUrl = "https://github.com/devKobe24/PortfolioBlog";
        final AddContentRequestDto userRequest = new AddContentRequestDto(representativeImageUrl,
            category, title, connectUrl);

        // 객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        // 설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Content> contents = portfolioBlogRepository.findAll();

        assertThat(contents.size()).isEqualTo(1);
        assertThat(contents.get(0).getTitle()).isEqualTo(title);
        assertThat(contents.get(0).getConnectUrl()).isEqualTo(connectUrl);
    }

    @DisplayName("findAllContents: 포트폴리오의 모든 컨텐츠 조회에 성공한다.")
    @Test
    public void findAllContents() throws Exception {
        // given
        final String url = "/api/contents";
        final String representativeImageUrl = "https://www.example.com/contentImage-1.png";
        final String category = "BACKEND";
        final String title = "포트폴리오 블로그";
        final String connectUrl = "https://www.github.com/devKobe24/PortfolioBlog";

        portfolioBlogRepository.save(Content.builder()
                                            .representativeImageUrl(representativeImageUrl)
                                            .category(category)
                                            .title(title)
                                            .connectUrl(connectUrl)
                                            .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
            .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].representativeImageUrl").value(representativeImageUrl))
            .andExpect(jsonPath("$[0].category").value(category))
            .andExpect(jsonPath("$[0].title").value(title))
            .andExpect(jsonPath("$[0].connectUrl").value(connectUrl));
    }

    @DisplayName("findContent: 콘텐츠 조회에 성공한다.")
    @Test
    public void findContent() throws Exception {
        // given
        final String url = "/api/contents/{id}";
        final String representativeImageUrl = "https://www.example.com/contentImage-1.png";
        final String category = "BACKEND";
        final String title = "포트폴리오 블로그";
        final String connectUrl = "https://www.github.com/devKobe24/PortfolioBlog";

        Content savedContent = portfolioBlogRepository.save(Content.builder()
                                                                   .representativeImageUrl(representativeImageUrl)
                                                                   .category(category)
                                                                   .title(title)
                                                                   .connectUrl(connectUrl)
                                                                   .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedContent.getId()));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.representativeImageUrl").value(representativeImageUrl))
            .andExpect(jsonPath("$.category").value(category))
            .andExpect(jsonPath("$.title").value(title))
            .andExpect(jsonPath("$.connectUrl").value(connectUrl));
    }

    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteContent() throws Exception {
        // given
        final String url = "/api/contents/{id}";
        final String representativeImageUrl = "https://www.example.com/contentImage-1.png";
        final String category = "BACKEND";
        final String title = "포트폴리오 블로그";
        final String connectUrl = "https://www.github.com/devKobe24/PortfolioBlog";

        Content savedArticle = portfolioBlogRepository.save(Content.builder()
            .representativeImageUrl(representativeImageUrl)
            .category(category)
            .title(title)
            .connectUrl(connectUrl)
            .build());

        // when
        mockMvc.perform(delete(url, savedArticle.getId()))
            .andExpect(status().isOk());

        // then
        List<Content> contents = portfolioBlogRepository.findAll();

        assertThat(contents).isEmpty();
    }

    @DisplayName("updateContent: 콘텐츠 수정에 성공한다.")
    @Test
    public void updateContent() throws Exception {
        // given
        final String url = "/api/contents/{id}";
        final String representativeImageUrl = "https://www.example.com/contentImage-1.png";
        final String category = "BACKEND";
        final String title = "포트폴리오 블로그";
        final String connectUrl = "https://www.github.com/devKobe24/PortfolioBlog";

        Content savedContent = portfolioBlogRepository.save(Content.builder()
            .representativeImageUrl(representativeImageUrl)
            .category(category)
            .title(title)
            .connectUrl(connectUrl)
            .build());

        final String newRepresentativeImageUrl = "https://www.example.com/new-content-image-1.png";
        final String newCategory = "iOS";
        final String newTitle = "모바일 포트폴리오 블로그";
        final String newConnectUrl = "https://www.github.com/devKobe24/iOSBlog";

        UpdateContentRequestDto requestDto = new UpdateContentRequestDto(newRepresentativeImageUrl, newCategory, newTitle, newConnectUrl);

        // when
        ResultActions result = mockMvc.perform(put(url, savedContent.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(requestDto)));

        // then
        result.andExpect(status().isOk());

        Content content = portfolioBlogRepository.findById(savedContent.getId()).get();

        assertThat(content.getRepresentativeImageUrl()).isEqualTo(newRepresentativeImageUrl);
        assertThat(content.getCategory()).isEqualTo(newCategory);
        assertThat(content.getTitle()).isEqualTo(newTitle);
        assertThat(content.getConnectUrl()).isEqualTo(newConnectUrl);
    }
}