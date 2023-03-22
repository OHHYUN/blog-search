package com.study.blogsearch.application.usecase;

import com.study.blogsearch.application.command.BlogSearchQueryCommand;
import com.study.blogsearch.domain.entity.BlogSearchResult;

public interface BlogSearchUseCase {

    BlogSearchResult searchBlog(BlogSearchQueryCommand command);
}
