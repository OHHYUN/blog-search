package com.study.blogsearch.infrastructure.serverapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.vo.SearchSource;
import com.study.blogsearch.domain.entity.vo.SortOrder;
import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import com.study.blogsearch.infrastructure.serverapi.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogSearchApiMockitoTest {

    @InjectMocks
    private BlogSearchApi blogSearchApi;

    @Mock
    private KakaoBlogSearch kakaoBlogSearch;

    @Mock
    private NaverBlogSearch naverBlogSearch;

    @Test
    @DisplayName("Kakao API 성공 테스트")
    void searchBlog_kakaoApiSuccess() {
        // Given
        KakaoBlogSearchResponse kakaoBlogSearchResponse = new KakaoBlogSearchResponse(
                new KakaoBlogSearchMeta(10, 10, true),
                List.of(
                        new KakaoBlogSearchDocument("title1", "contents1", "url1", "blogname1", "thumbnail1", "2022-03-22T15:10:30.000+09:00")
                )
        );

        when(kakaoBlogSearch.callKakaoAPI(any(KakaoBlogSearchRequest.class))).thenReturn(Mono.just(kakaoBlogSearchResponse));

        // When
        BlogSearchResult result = blogSearchApi.searchBlog(new BlogSearchQuery("test", SortOrder.RECENCY, 1));

        // Then
        assertNotNull(result);
        assertEquals(SearchSource.KAKAO, result.getMeta().getSearchSource());
        assertEquals(10, result.getMeta().getTotalItems());
        assertEquals(1, result.getMeta().getCurrentPage());
        assertFalse(result.getBlogPosts().isEmpty());
        assertEquals("url1", result.getBlogPosts().get(0).getPostUrl());
        assertEquals("title1", result.getBlogPosts().get(0).getPostTitle());
    }


    @Test
    @DisplayName("실패 테스트")
    void searchBlog_bothServicesFail_throwsBlogSearchException() {
        // 모든 KakaoBlogSearch 호출이 실패하도록 설정
        when(kakaoBlogSearch.callKakaoAPI(any(KakaoBlogSearchRequest.class)))
                .thenReturn(Mono.error(new BlogSearchException(BlogSearchErrorCode.KAKAO_BLOG_SERVER_ERROR)));

        // 모든 NaverBlogSearch 호출이 실패하도록 설정
        when(naverBlogSearch.callNaverAPI(any(NaverBlogSearchRequest.class)))
                .thenReturn(Mono.error(new BlogSearchException(BlogSearchErrorCode.NAVER_BLOG_SERVER_ERROR)));

        // 검색 쿼리 생성
        BlogSearchQuery query = new BlogSearchQuery("test", SortOrder.RECENCY, 1);

        // 두 서비스 모두 실패하는 경우 BlogSearchException이 발생해야 함
        assertThrows(BlogSearchException.class, () -> blogSearchApi.searchBlog(query), "Expected BlogSearchException when both services fail");
    }
}