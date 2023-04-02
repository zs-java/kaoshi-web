package com.xzcoder.kaoshi.common.po;

/**
 * PageBean
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class PageBean {

    private Integer pageCode = 1;//当前页pageCode
    //	private Integer totalPage;//总页数totalPage
    private Integer totalRecored;//总记录数
    private Integer pageSize;//每页记录数
    private String queryUrl;//查询条件url


    public PageBean() {
    }

    public PageBean(Integer pageCode, Integer pageSize) {
        this.pageCode = pageCode;
        this.pageSize = pageSize;
    }

    public Integer getPageCode() {
        return pageCode;
    }

    public Integer getTotalPage() {
        //通过总记录数和每页记录数来计算总页数
        int tp = totalRecored / pageSize;
        return totalRecored % pageSize == 0 ? tp : tp + 1;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecored() {
        return totalRecored;
    }

    public Integer getStartIndex() {
        return (pageCode - 1) * pageSize;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public void setPageCode(Integer pageCode) {
        this.pageCode = pageCode;
    }

    public void setTotalRecored(Integer totalRecored) {
        this.totalRecored = totalRecored;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageBean [pageCode=" + pageCode + ", totalRecored="
                + totalRecored + ", pageSize=" + pageSize + ", queryUrl="
                + queryUrl + "]";
    }

}
