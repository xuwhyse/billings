package com.billings.billingsystem.model;

import java.io.Serializable;

public class WeekSignDescBean implements Serializable {
    /**
     * ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 20150713  每个自然周，取星期一的日子为id
     *
     * @mbggenerated
     */
    private Integer weekId;

    /**
     * 本周，周一总签到人数。如(在积分分法的时候，需要count周签到表矫正这个数。  同时，积分池根据
            这个字段计算得出=10000+num*20)
     *
     * @mbggenerated
     */
    private Integer signNumMon;

    private Integer signNumTue;

    private Integer signNumWed;

    private Integer signNumThur;

    private Integer signNumFri;

    private Integer signNumSat;

    private Integer signNumSun;

    /**
     * 参与本周大抽奖的人数，集齐七天符合要求的人数
     *
     * @mbggenerated
     */
    private Integer lotteryNum;

    /**
     * 跑出中奖名单后，字段为0：待审查。等编辑确认后，点击确认 修改为1：生效。此时前台能出现中间名单。
            中奖名单见PRIS_WeekSign
     *
     * @mbggenerated
     */
    private Short lotteryState;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Long updateTime;

    private Long createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public Integer getSignNumMon() {
        return signNumMon;
    }

    public void setSignNumMon(Integer signNumMon) {
        this.signNumMon = signNumMon;
    }

    public Integer getSignNumTue() {
        return signNumTue;
    }

    public void setSignNumTue(Integer signNumTue) {
        this.signNumTue = signNumTue;
    }

    public Integer getSignNumWed() {
        return signNumWed;
    }

    public void setSignNumWed(Integer signNumWed) {
        this.signNumWed = signNumWed;
    }

    public Integer getSignNumThur() {
        return signNumThur;
    }

    public void setSignNumThur(Integer signNumThur) {
        this.signNumThur = signNumThur;
    }

    public Integer getSignNumFri() {
        return signNumFri;
    }

    public void setSignNumFri(Integer signNumFri) {
        this.signNumFri = signNumFri;
    }

    public Integer getSignNumSat() {
        return signNumSat;
    }

    public void setSignNumSat(Integer signNumSat) {
        this.signNumSat = signNumSat;
    }

    public Integer getSignNumSun() {
        return signNumSun;
    }

    public void setSignNumSun(Integer signNumSun) {
        this.signNumSun = signNumSun;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public Short getLotteryState() {
        return lotteryState;
    }

    public void setLotteryState(Short lotteryState) {
        this.lotteryState = lotteryState;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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
        WeekSignDescBean other = (WeekSignDescBean) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeekId() == null ? other.getWeekId() == null : this.getWeekId().equals(other.getWeekId()))
            && (this.getSignNumMon() == null ? other.getSignNumMon() == null : this.getSignNumMon().equals(other.getSignNumMon()))
            && (this.getSignNumTue() == null ? other.getSignNumTue() == null : this.getSignNumTue().equals(other.getSignNumTue()))
            && (this.getSignNumWed() == null ? other.getSignNumWed() == null : this.getSignNumWed().equals(other.getSignNumWed()))
            && (this.getSignNumThur() == null ? other.getSignNumThur() == null : this.getSignNumThur().equals(other.getSignNumThur()))
            && (this.getSignNumFri() == null ? other.getSignNumFri() == null : this.getSignNumFri().equals(other.getSignNumFri()))
            && (this.getSignNumSat() == null ? other.getSignNumSat() == null : this.getSignNumSat().equals(other.getSignNumSat()))
            && (this.getSignNumSun() == null ? other.getSignNumSun() == null : this.getSignNumSun().equals(other.getSignNumSun()))
            && (this.getLotteryNum() == null ? other.getLotteryNum() == null : this.getLotteryNum().equals(other.getLotteryNum()))
            && (this.getLotteryState() == null ? other.getLotteryState() == null : this.getLotteryState().equals(other.getLotteryState()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeekId() == null) ? 0 : getWeekId().hashCode());
        result = prime * result + ((getSignNumMon() == null) ? 0 : getSignNumMon().hashCode());
        result = prime * result + ((getSignNumTue() == null) ? 0 : getSignNumTue().hashCode());
        result = prime * result + ((getSignNumWed() == null) ? 0 : getSignNumWed().hashCode());
        result = prime * result + ((getSignNumThur() == null) ? 0 : getSignNumThur().hashCode());
        result = prime * result + ((getSignNumFri() == null) ? 0 : getSignNumFri().hashCode());
        result = prime * result + ((getSignNumSat() == null) ? 0 : getSignNumSat().hashCode());
        result = prime * result + ((getSignNumSun() == null) ? 0 : getSignNumSun().hashCode());
        result = prime * result + ((getLotteryNum() == null) ? 0 : getLotteryNum().hashCode());
        result = prime * result + ((getLotteryState() == null) ? 0 : getLotteryState().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", weekId=").append(weekId);
        sb.append(", signNumMon=").append(signNumMon);
        sb.append(", signNumTue=").append(signNumTue);
        sb.append(", signNumWed=").append(signNumWed);
        sb.append(", signNumThur=").append(signNumThur);
        sb.append(", signNumFri=").append(signNumFri);
        sb.append(", signNumSat=").append(signNumSat);
        sb.append(", signNumSun=").append(signNumSun);
        sb.append(", lotteryNum=").append(lotteryNum);
        sb.append(", lotteryState=").append(lotteryState);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}