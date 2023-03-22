package com.study.blogsearch.domain.extapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;

public interface BlogSearch {
    BlogSearchResult searchBlog(BlogSearchQuery query);

}
