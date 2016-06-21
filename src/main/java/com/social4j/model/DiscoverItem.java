package com.social4j.model;

import java.io.Serializable;

public class DiscoverItem implements Serializable {
    private Integer discoverItemId;

    /**
     * 1:nav;  2:notice; 3:banner; 4:info
     *
     * @mbggenerated
     */
    private Integer discoverType;

    /**
     * 发现版本code,每个版本不同界面
     *
     * @mbggenerated
     */
    private String discoverCode;

    /**
     * 显示题目文字
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 图片连接地址
     *
     * @mbggenerated
     */
    private String image;

    /**
     * 描述
     *
     * @mbggenerated
     */
    private String described;

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 跳转地址
     *
     * @mbggenerated
     */
    private String link;

    /**
     * 地区
     *
     * @mbggenerated
     */
    private String region;

    private Long createtime;

    private Long updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getDiscoverItemId() {
        return discoverItemId;
    }

    public void setDiscoverItemId(Integer discoverItemId) {
        this.discoverItemId = discoverItemId;
    }

    public Integer getDiscoverType() {
        return discoverType;
    }

    public void setDiscoverType(Integer discoverType) {
        this.discoverType = discoverType;
    }

    public String getDiscoverCode() {
        return discoverCode;
    }

    public void setDiscoverCode(String discoverCode) {
        this.discoverCode = discoverCode == null ? null : discoverCode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described == null ? null : described.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }
}