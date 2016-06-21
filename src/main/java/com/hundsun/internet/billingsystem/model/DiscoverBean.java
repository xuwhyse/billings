package com.hundsun.internet.billingsystem.model;

import java.io.Serializable;
import java.util.Date;

public class DiscoverBean implements Serializable {
    private Integer discoverId;

    /**
     * 发现版本,每个版本不同界面
     *
     * @mbggenerated
     */
    private String discoverVersion;

    /**
     * 语言
     *
     * @mbggenerated
     */
    private String language;

    /**
     * 地区
     *
     * @mbggenerated
     */
    private String region;

    /**
     * 1:nav;  2:notice; 3:banner; 4:info
     *
     * @mbggenerated
     */
    private Integer discoverType;

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 注释
     *
     * @mbggenerated
     */
    private String mark;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getDiscoverId() {
        return discoverId;
    }

    public void setDiscoverId(Integer discoverId) {
        this.discoverId = discoverId;
    }

    public String getDiscoverVersion() {
        return discoverVersion;
    }

    public void setDiscoverVersion(String discoverVersion) {
        this.discoverVersion = discoverVersion == null ? null : discoverVersion.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getDiscoverType() {
        return discoverType;
    }

    public void setDiscoverType(Integer discoverType) {
        this.discoverType = discoverType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}