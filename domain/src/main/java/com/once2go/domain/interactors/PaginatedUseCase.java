package com.once2go.domain.interactors;

import com.once2go.model.Config;

/**
 * Created by denyskarpov on 03.08.16.
 */
public abstract class PaginatedUseCase<T> extends UseCase<T> {

    protected int page;
    protected int limit;

    public PaginatedUseCase() {
        page = Config.DEFAULT_PAGINATION_PAGE;
        limit = Config.DEFAULT_PAGINATION_LIMIT;
    }

    public void setQueryCredentials(int page) {
        setQueryCredentials(page, Config.DEFAULT_PAGINATION_LIMIT);
    }

    public void setQueryCredentials(int page, int limit) {
        if (page >= Config.DEFAULT_PAGINATION_PAGE) {
            this.page = page;
        } else {
            throw new IllegalArgumentException("Page can't be less than default");
        }
        if (limit > 0) {
            this.limit = limit;
        } else {
            throw new IllegalArgumentException("Limit can't be 0 or less");
        }
    }
}
