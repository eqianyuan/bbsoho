package cn.eqianyuan.bean;

import cn.eqianyuan.bean.dto.Page;

import java.util.List;


/**
 * PageResponse
 */
public class PageResponse {

    private long totalCount;
    private long pageCount;
    private int pageSize;
    private int pageNo;
    private List<?> list;

    public PageResponse(Page page) {
        this.pageNo = page.getPageNo();
        this.pageSize = page.getPageSize();
    }
    public PageResponse(Page page, List<?> list ){
        this(page);
        this.totalCount = page.getTotalRow();
        this.pageCount = page.getTotalPage();
        this.list = list;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
