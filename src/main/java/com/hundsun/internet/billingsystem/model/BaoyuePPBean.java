package com.hundsun.internet.billingsystem.model;

import java.io.Serializable;

public class BaoyuePPBean implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 包类型，0：出版图书；1：原创图书；2:全场包月
     *
     * @mbggenerated
     */
    private Byte createby;

    /**
     * 包名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 推荐语
     *
     * @mbggenerated
     */
    private String editorrecommend;

    /**
     * 书籍数量
     *
     * @mbggenerated
     */
    private Integer booknum;

    /**
     * 订购数量
     *
     * @mbggenerated
     */
    private Integer buynum;

    /**
     * 订购权重
     *
     * @mbggenerated
     */
    private Integer buynumpriority;

    /**
     * 记录创建时间
     *
     * @mbggenerated
     */
    private Long createtime;

    /**
     * 记录更新时间
     *
     * @mbggenerated
     */
    private Long updatetime;

    /**
     * 状态。0，正常；-1，删除;  1,不可购买
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * 包的配图
     *
     * @mbggenerated
     */
    private String cover;

    /**
     * 包月中所有书籍的阅点总价，展示用
     *
     * @mbggenerated
     */
    private Integer totalvalue;

    /**
     * 开通包月的正文介绍导语
     *
     * @mbggenerated
     */
    private String opendesc;

    /**
     * 开始时间，只对全场包有效
     *
     * @mbggenerated
     */
    private Long starttime;

    /**
     * 结束时间，只对全场包有效
     *
     * @mbggenerated
     */
    private Long endtime;

    /**
     * 价格字段，json数组[{"days":30,"price":1000,"rprice":1000"},{"days":60,"price":1000","rprice",900}]
     *
     * @mbggenerated
     */
    private String priceinfo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getCreateby() {
        return createby;
    }

    public void setCreateby(Byte createby) {
        this.createby = createby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEditorrecommend() {
        return editorrecommend;
    }

    public void setEditorrecommend(String editorrecommend) {
        this.editorrecommend = editorrecommend == null ? null : editorrecommend.trim();
    }

    public Integer getBooknum() {
        return booknum;
    }

    public void setBooknum(Integer booknum) {
        this.booknum = booknum;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public Integer getBuynumpriority() {
        return buynumpriority;
    }

    public void setBuynumpriority(Integer buynumpriority) {
        this.buynumpriority = buynumpriority;
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(Integer totalvalue) {
        this.totalvalue = totalvalue;
    }

    public String getOpendesc() {
        return opendesc;
    }

    public void setOpendesc(String opendesc) {
        this.opendesc = opendesc == null ? null : opendesc.trim();
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getPriceinfo() {
        return priceinfo;
    }

    public void setPriceinfo(String priceinfo) {
        this.priceinfo = priceinfo == null ? null : priceinfo.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaoyuePPBean other = (BaoyuePPBean) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEditorrecommend() == null ? other.getEditorrecommend() == null : this.getEditorrecommend().equals(other.getEditorrecommend()))
            && (this.getBooknum() == null ? other.getBooknum() == null : this.getBooknum().equals(other.getBooknum()))
            && (this.getBuynum() == null ? other.getBuynum() == null : this.getBuynum().equals(other.getBuynum()))
            && (this.getBuynumpriority() == null ? other.getBuynumpriority() == null : this.getBuynumpriority().equals(other.getBuynumpriority()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
            && (this.getTotalvalue() == null ? other.getTotalvalue() == null : this.getTotalvalue().equals(other.getTotalvalue()))
            && (this.getOpendesc() == null ? other.getOpendesc() == null : this.getOpendesc().equals(other.getOpendesc()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getPriceinfo() == null ? other.getPriceinfo() == null : this.getPriceinfo().equals(other.getPriceinfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateby() == null) ? 0 : getCreateby().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEditorrecommend() == null) ? 0 : getEditorrecommend().hashCode());
        result = prime * result + ((getBooknum() == null) ? 0 : getBooknum().hashCode());
        result = prime * result + ((getBuynum() == null) ? 0 : getBuynum().hashCode());
        result = prime * result + ((getBuynumpriority() == null) ? 0 : getBuynumpriority().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getTotalvalue() == null) ? 0 : getTotalvalue().hashCode());
        result = prime * result + ((getOpendesc() == null) ? 0 : getOpendesc().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getPriceinfo() == null) ? 0 : getPriceinfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createby=").append(createby);
        sb.append(", name=").append(name);
        sb.append(", editorrecommend=").append(editorrecommend);
        sb.append(", booknum=").append(booknum);
        sb.append(", buynum=").append(buynum);
        sb.append(", buynumpriority=").append(buynumpriority);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", cover=").append(cover);
        sb.append(", totalvalue=").append(totalvalue);
        sb.append(", opendesc=").append(opendesc);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", priceinfo=").append(priceinfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}