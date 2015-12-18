package fr.cmm.helper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.util.Arrays.asList;

public class Pagination {
    // 1 based page index
    private int pageIndex;

    private int pageSize;

    private long count;

    public static final int PAGINATION_SIZE = 10;

    public int getPreviousPageIndex() {
        return isFirstPage() ? pageIndex : pageIndex - 1;
    }

    public int getNextPageIndex() {
        return isLastPage() ? pageIndex : pageIndex + 1;
    }

    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    public boolean isLastPage() {
        return pageIndex * pageSize >= count;
    }

    public int getPageCount() {
        int dividedCount = (int) floor(count / pageSize);
        int pageNumber = ((int)count % pageSize == 0)? dividedCount:dividedCount+1;
        return pageNumber;
    }

    public List<Integer> getPages() {
        int index = this.getPageIndex();
        int beginning;

        if ( index + floorDiv(PAGINATION_SIZE, 2) >= this.getCount()){
            beginning = ( max(1, 2*this.getPageCount() - PAGINATION_SIZE -index));
        }
        else {
            beginning = (int) max(1, index - floor(PAGINATION_SIZE/2-1));
        }

        int end = min(this.getPageCount(), beginning + PAGINATION_SIZE-1);
        List<Integer> resultList = new ArrayList<>();
        int x;
        for (x=beginning; x <= end; x=x+1){
            resultList.add(x);
        }
        return resultList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
