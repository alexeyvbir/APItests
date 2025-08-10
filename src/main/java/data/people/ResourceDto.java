package data.people;

import java.util.List;

public class ResourceDto {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<DataUserDto> data;
    private SupportDto support;

    public ResourceDto() {
    }

    public ResourceDto(Integer page, Integer per_page, Integer total, Integer total_pages, List<DataUserDto> userDto, SupportDto supportDto) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<DataUserDto> getData() {
        return data;
    }

    public void setUserDto(List<DataUserDto> userDto) {
        this.data = userDto;
    }

    public SupportDto getSupport() {
        return support;
    }

    public void setSupportDto(SupportDto supportDto) {
        this.support = supportDto;
    }
}
