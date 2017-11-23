package com.agile.common.base;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by 佟盟 on 2017/11/21
 */
public class Page implements org.springframework.data.domain.Page {
    private List content;
    private Pageable pageable = new Pageable();
    private boolean last;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private Sort sort = new Sort(org.springframework.data.domain.Sort.Direction.ASC,"row");
    private boolean first;
    private int numberOfElements;

    @NotNull
    @Override
    public Iterator iterator() {
        return content.iterator();
    }

    public static class Sort extends org.springframework.data.domain.Sort {
        private static final long serialVersionUID = -7064100547980669027L;
        private boolean sorted = false;
        private boolean unsorted = true;

        public Sort(Direction direction, String... properties) {
            super(direction, properties);
        }
    }



    private class Pageable implements org.springframework.data.domain.Pageable {
        private Page.Sort sort;
        private int offset;
        private int pageSize;
        private int pageNumber;
        private boolean paged;
        private boolean unpaged;

        @Override
        public int getPageNumber() {
            return this.pageNumber;
        }

        @Override
        public int getPageSize() {
            return this.pageSize;
        }

        @Override
        public long getOffset() {
            return this.offset;
        }

        @NotNull
        @Override
        public org.springframework.data.domain.Sort getSort() {
            return this.sort;
        }

        @NotNull
        @Override
        public org.springframework.data.domain.Pageable next() {
            return null;
        }

        @NotNull
        @Override
        public org.springframework.data.domain.Pageable previousOrFirst() {
            return null;
        }

        @NotNull
        @Override
        public org.springframework.data.domain.Pageable first() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    }



    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getOffset() {
        return this.pageable.offset;
    }

    public void setOffset(int offset) {
        this.pageable.offset = offset;
    }

    public int getPageSize() {
        return this.pageable.pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.pageable.pageSize = size;
    }

    public int getPageNumber() {
        return this.pageable.pageNumber;
    }

    public boolean isPaged() {
        return this.pageable.paged;
    }

    public void setPaged(boolean paged) {
        this.pageable.paged = paged;
    }

    public boolean isUnpaged() {
        return this.pageable.unpaged;
    }

    public void setUnpaged(boolean unpaged) {
        this.pageable.unpaged = unpaged;
    }
    public boolean isSorted() {
        return this.sort.sorted;
    }

    public void setSorted(boolean sorted) {
        this.sort.sorted = sorted;
    }

    public boolean isUnsorted() {
        return this.sort.unsorted;
    }

    public void setUnsorted(boolean unsorted) {
        this.sort.unsorted = unsorted;
    }

    public boolean isLast() {
        return last;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    @NotNull
    @Override
    public org.springframework.data.domain.Page map(@NotNull Function function) {
        return null;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.pageable.pageNumber = number;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
        this.pageable.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public List getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public org.springframework.data.domain.Pageable nextPageable() {
        return null;
    }

    @Override
    public org.springframework.data.domain.Pageable previousPageable() {
        return null;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
