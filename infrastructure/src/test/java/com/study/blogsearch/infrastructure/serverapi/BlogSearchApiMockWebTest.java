package com.study.blogsearch.infrastructure.serverapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.vo.SortOrder;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BlogSearchApiMockWebTest {

    MockWebServer kakaoMockWebServer;

    MockWebServer naverMockWebServer;

    private BlogSearchApi blogSearchApi;

    @BeforeEach
    void setUp() {
        kakaoMockWebServer = new MockWebServer();
        naverMockWebServer = new MockWebServer();

        try {
            kakaoMockWebServer.start();
            naverMockWebServer.start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to start MockWebServer", e);
        }
        WebClient kakaoWebClient = WebClient.builder()
                .baseUrl(kakaoMockWebServer.url("/").toString())
                .build();

        WebClient naverWebClient = WebClient.builder()
                .baseUrl(naverMockWebServer.url("/").toString())
                .build();

        KakaoBlogSearch kakaoBlogSearch = new KakaoBlogSearch(kakaoWebClient);
        NaverBlogSearch naverBlogSearch = new NaverBlogSearch(naverWebClient);

        blogSearchApi = new BlogSearchApi(kakaoBlogSearch, naverBlogSearch);
    }

    @AfterEach
    void tearDown() throws IOException {
        kakaoMockWebServer.shutdown();
        naverMockWebServer.shutdown();
    }

    @Test
    @DisplayName("카카오 API가 실패하면 네이버 API에 요청하는 테스트")
    void searchBlog_whenKakaoFails_shouldCallNaver() {
        // Given
        BlogSearchQuery query = BlogSearchQuery.builder().query("test").sortOrder(SortOrder.RECENCY).build();
        MockResponse fixedResponse = new MockResponse().setResponseCode(500);
        kakaoMockWebServer.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                return fixedResponse;
            }
        });

        String naverResponseJson = "{\n" +
                "    \"lastBuildDate\": \"Wed, 22 Mar 2023 14:08:59 +0900\",\n" +
                "    \"total\": 23056181,\n" +
                "    \"start\": 1,\n" +
                "    \"display\": 10,\n" +
                "    \"items\": []\n" +
                "}";
        naverMockWebServer.enqueue(new MockResponse()
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(naverResponseJson)
                .setResponseCode(200));
        naverMockWebServer.enqueue(new MockResponse().setBody(naverResponseJson).setResponseCode(200));

        // When
        BlogSearchResult result = blogSearchApi.searchBlog(query);

        // Then
        assertEquals(2, kakaoMockWebServer.getRequestCount());
        assertEquals(1, naverMockWebServer.getRequestCount());
        assertNotNull(result);
    }
}