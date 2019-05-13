package javabean;

import table.BX_Books;

import java.util.List;

public class PageBean {
    private Integer pagesize = 20;
    private Integer totalbook;
    private Integer totalpages;
    private Integer pages;
    private List<BX_Books> list;

    public Integer getTotalbook() {
        return this.totalbook;
    }

    public void setTotalbook(final Integer totalbook) {
        this.totalbook = totalbook;
    }

    public Integer getTotalpages() {
        return this.totalpages;
    }

    public void setTotalpages(final Integer totalpages) {
        this.totalpages = totalpages;
    }

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(final Integer pages) {
        this.pages = pages;
    }

    public List<BX_Books> getList() {
        return this.list;
    }

    public void setList(final List<BX_Books> list) {
        this.list = list;
    }
    public Integer getPagesize() {
        return this.pagesize;
    }
}
