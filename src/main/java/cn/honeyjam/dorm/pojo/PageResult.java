package cn.honeyjam.dorm.pojo;
import java.util.List;

/**
 * Created by 黑客Jack on 2019/4/22.
 */
public class PageResult<T> {
    private Long totalCount;// 总条数
    private Long totalPage;// 总页数
    private List<T> items;// 当前页数据
    private Integer currentPage;//当前页码
    private Integer rows;//显示的行数


    public PageResult() {
    }

    public PageResult(Long totalCount, List<T> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    public PageResult(Long totalCount, Long totalPage, List<T> items, Integer currentPage, Integer rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.items = items;
        this.currentPage = currentPage;
        this.rows = rows;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public PageResult(Long totalCount, Long totalPage, List<T> items) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", items=" + items +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

