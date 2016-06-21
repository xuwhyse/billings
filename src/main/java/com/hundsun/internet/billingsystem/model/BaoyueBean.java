package com.hundsun.internet.billingsystem.model;

import java.io.Serializable;

public class BaoyueBean implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 包月包id
     *
     * @mbggenerated
     */
    private Long packageid;

    /**
     * 书的id
     *
     * @mbggenerated
     */
    private String sourceuuid;

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
     * 记录被删除包的时间
     *
     * @mbggenerated
     */
    private Long removetime;

    /**
     * 状态。0，正常；-1，删除
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * 排序值，初始化是订阅数；根据用户拉取行为更新数据
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 完结。0，未完结；1，完结
     *
     * @mbggenerated
     */
    private Byte finished;

    /**
     * 最新章节更新时间
     *
     * @mbggenerated
     */
    private Long latestpublishtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPackageid() {
        return packageid;
    }

    public void setPackageid(Long packageid) {
        this.packageid = packageid;
    }

    public String getSourceuuid() {
        return sourceuuid;
    }

    public void setSourceuuid(String sourceuuid) {
        this.sourceuuid = sourceuuid == null ? null : sourceuuid.trim();
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

    public Long getRemovetime() {
        return removetime;
    }

    public void setRemovetime(Long removetime) {
        this.removetime = removetime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getFinished() {
        return finished;
    }

    public void setFinished(Byte finished) {
        this.finished = finished;
    }

    public Long getLatestpublishtime() {
        return latestpublishtime;
    }

    public void setLatestpublishtime(Long latestpublishtime) {
        this.latestpublishtime = latestpublishtime;
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
        BaoyueBean other = (BaoyueBean) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPackageid() == null ? other.getPackageid() == null : this.getPackageid().equals(other.getPackageid()))
            && (this.getSourceuuid() == null ? other.getSourceuuid() == null : this.getSourceuuid().equals(other.getSourceuuid()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getRemovetime() == null ? other.getRemovetime() == null : this.getRemovetime().equals(other.getRemovetime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getFinished() == null ? other.getFinished() == null : this.getFinished().equals(other.getFinished()))
            && (this.getLatestpublishtime() == null ? other.getLatestpublishtime() == null : this.getLatestpublishtime().equals(other.getLatestpublishtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPackageid() == null) ? 0 : getPackageid().hashCode());
        result = prime * result + ((getSourceuuid() == null) ? 0 : getSourceuuid().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getRemovetime() == null) ? 0 : getRemovetime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getLatestpublishtime() == null) ? 0 : getLatestpublishtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", packageid=").append(packageid);
        sb.append(", sourceuuid=").append(sourceuuid);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", removetime=").append(removetime);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", finished=").append(finished);
        sb.append(", latestpublishtime=").append(latestpublishtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}