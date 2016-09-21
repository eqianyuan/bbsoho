package cn.eqianyuan.bean.dto;

/**
 * 分页对象
 * Created by jason on 2016-05-28.
 */
public class Page {

    /**
     * 分页页码
     */
    private int pageNo = 1;

    /**
     * 分页每页显示条目
     */
    private int pageSize = 10;

    /**
     * 共多少行
     */
    private int totalRow;

    /**
     * 共多少页
     */
    private int totalPage;

    /**
     * 实际查询位置
     */
    private int start = 0;

    /**
     * 分页排序字段
     */
    private String orderByColumn;

    /**
     * 分页排序对应字段的排序方式
     */
    private String orderByType;

    public Page(){}

    public Page(int pageNo, int pageSize) {
        this.start = (pageNo <= 0 ? 0 : pageNo - 1) * pageSize;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Page(int pageNo, int pageSize, String orderByColumn, String orderByType) {
        this(pageNo, pageSize);
        this.orderByColumn = orderByColumn;
        this.orderByType = orderByType;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.start = (pageNo <= 0 ? 1 : pageNo - 1) * pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(String orderByType) {
        this.orderByType = orderByType;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        totalPage = (totalRow + pageSize - 1) / pageSize;
        this.totalRow = totalRow;
        if (this.totalPage < this.pageNo) {
            this.pageNo = this.totalPage;
            this.start = this.pageSize * (this.totalPage - 1);
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
