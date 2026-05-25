package com.arthur.model;

import java.util.List;

public class ApiResponse {
    private List<Skin> results;
    private String nextCursor;
    private boolean hasMore;

    public List<Skin> getResults() {
        return results;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}