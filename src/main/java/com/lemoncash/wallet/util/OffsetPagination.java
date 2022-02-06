package com.lemoncash.wallet.util;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetPagination implements Pageable {

    private static final long serialVersionUID = -25822477129613575L;

    private final int limit;
    private final long offset;
    private final Sort sort;

    public OffsetPagination(long offset, int limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = Sort.unsorted();
    }

    @Override
    public int getPageNumber() {
        return Math.toIntExact(offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetPagination(getOffset() + getPageSize(), getPageSize());
    }

    public OffsetPagination previous() {
        return hasPrevious() ? new OffsetPagination(getOffset() - getPageSize(), getPageSize()) : this;
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetPagination(0, getPageSize());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetPagination((long) pageNumber * getPageSize(), getPageSize());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }


}