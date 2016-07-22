package com.billings.billingsystem.model;

import java.io.Serializable;

public class WeekSignBean implements Serializable {
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
     * 和weekid组合成唯一键
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 星期一分数
     *
     * @mbggenerated
     */
    private Integer mon;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateMon;

    private Integer tue;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateTue;

    private Integer wed;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateWed;

    private Integer thur;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateThur;

    private Integer fri;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateFri;

    private Integer sat;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateSat;

    private Integer sun;

    /**
     * 0:未签到 1：已签到 2：补签
     *
     * @mbggenerated
     */
    private Short stateSun;

    /**
     * 奖品code,默认为0，非0就是此人本次中奖了
     *
     * @mbggenerated
     */
    private String prizeCode;

    /**
     * 当日补签机会，每次充值补签次数+1.更新RetroactiveTime为当天时间比如20150713。如果发现时间不是今天，
            则清0后机会再加一
     *
     * @mbggenerated
     */
    private Integer retroactiveNum;

    /**
     * 每次充值，更新这个字段格式：20150713，八位
     *
     * @mbggenerated
     */
    private Integer retroactiveTime;

    /**
     * 抽奖时间，用户本周全部签到有资格参与抽奖的最后一次签到时间。 用于排序用
     *
     * @mbggenerated
     */
    private Long drawtime;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMon() {
        return mon;
    }

    public void setMon(Integer mon) {
        this.mon = mon;
    }

    public Short getStateMon() {
        return stateMon;
    }

    public void setStateMon(Short stateMon) {
        this.stateMon = stateMon;
    }

    public Integer getTue() {
        return tue;
    }

    public void setTue(Integer tue) {
        this.tue = tue;
    }

    public Short getStateTue() {
        return stateTue;
    }

    public void setStateTue(Short stateTue) {
        this.stateTue = stateTue;
    }

    public Integer getWed() {
        return wed;
    }

    public void setWed(Integer wed) {
        this.wed = wed;
    }

    public Short getStateWed() {
        return stateWed;
    }

    public void setStateWed(Short stateWed) {
        this.stateWed = stateWed;
    }

    public Integer getThur() {
        return thur;
    }

    public void setThur(Integer thur) {
        this.thur = thur;
    }

    public Short getStateThur() {
        return stateThur;
    }

    public void setStateThur(Short stateThur) {
        this.stateThur = stateThur;
    }

    public Integer getFri() {
        return fri;
    }

    public void setFri(Integer fri) {
        this.fri = fri;
    }

    public Short getStateFri() {
        return stateFri;
    }

    public void setStateFri(Short stateFri) {
        this.stateFri = stateFri;
    }

    public Integer getSat() {
        return sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public Short getStateSat() {
        return stateSat;
    }

    public void setStateSat(Short stateSat) {
        this.stateSat = stateSat;
    }

    public Integer getSun() {
        return sun;
    }

    public void setSun(Integer sun) {
        this.sun = sun;
    }

    public Short getStateSun() {
        return stateSun;
    }

    public void setStateSun(Short stateSun) {
        this.stateSun = stateSun;
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode == null ? null : prizeCode.trim();
    }

    public Integer getRetroactiveNum() {
        return retroactiveNum;
    }

    public void setRetroactiveNum(Integer retroactiveNum) {
        this.retroactiveNum = retroactiveNum;
    }

    public Integer getRetroactiveTime() {
        return retroactiveTime;
    }

    public void setRetroactiveTime(Integer retroactiveTime) {
        this.retroactiveTime = retroactiveTime;
    }

    public Long getDrawtime() {
        return drawtime;
    }

    public void setDrawtime(Long drawtime) {
        this.drawtime = drawtime;
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
        WeekSignBean other = (WeekSignBean) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeekId() == null ? other.getWeekId() == null : this.getWeekId().equals(other.getWeekId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMon() == null ? other.getMon() == null : this.getMon().equals(other.getMon()))
            && (this.getStateMon() == null ? other.getStateMon() == null : this.getStateMon().equals(other.getStateMon()))
            && (this.getTue() == null ? other.getTue() == null : this.getTue().equals(other.getTue()))
            && (this.getStateTue() == null ? other.getStateTue() == null : this.getStateTue().equals(other.getStateTue()))
            && (this.getWed() == null ? other.getWed() == null : this.getWed().equals(other.getWed()))
            && (this.getStateWed() == null ? other.getStateWed() == null : this.getStateWed().equals(other.getStateWed()))
            && (this.getThur() == null ? other.getThur() == null : this.getThur().equals(other.getThur()))
            && (this.getStateThur() == null ? other.getStateThur() == null : this.getStateThur().equals(other.getStateThur()))
            && (this.getFri() == null ? other.getFri() == null : this.getFri().equals(other.getFri()))
            && (this.getStateFri() == null ? other.getStateFri() == null : this.getStateFri().equals(other.getStateFri()))
            && (this.getSat() == null ? other.getSat() == null : this.getSat().equals(other.getSat()))
            && (this.getStateSat() == null ? other.getStateSat() == null : this.getStateSat().equals(other.getStateSat()))
            && (this.getSun() == null ? other.getSun() == null : this.getSun().equals(other.getSun()))
            && (this.getStateSun() == null ? other.getStateSun() == null : this.getStateSun().equals(other.getStateSun()))
            && (this.getPrizeCode() == null ? other.getPrizeCode() == null : this.getPrizeCode().equals(other.getPrizeCode()))
            && (this.getRetroactiveNum() == null ? other.getRetroactiveNum() == null : this.getRetroactiveNum().equals(other.getRetroactiveNum()))
            && (this.getRetroactiveTime() == null ? other.getRetroactiveTime() == null : this.getRetroactiveTime().equals(other.getRetroactiveTime()))
            && (this.getDrawtime() == null ? other.getDrawtime() == null : this.getDrawtime().equals(other.getDrawtime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeekId() == null) ? 0 : getWeekId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMon() == null) ? 0 : getMon().hashCode());
        result = prime * result + ((getStateMon() == null) ? 0 : getStateMon().hashCode());
        result = prime * result + ((getTue() == null) ? 0 : getTue().hashCode());
        result = prime * result + ((getStateTue() == null) ? 0 : getStateTue().hashCode());
        result = prime * result + ((getWed() == null) ? 0 : getWed().hashCode());
        result = prime * result + ((getStateWed() == null) ? 0 : getStateWed().hashCode());
        result = prime * result + ((getThur() == null) ? 0 : getThur().hashCode());
        result = prime * result + ((getStateThur() == null) ? 0 : getStateThur().hashCode());
        result = prime * result + ((getFri() == null) ? 0 : getFri().hashCode());
        result = prime * result + ((getStateFri() == null) ? 0 : getStateFri().hashCode());
        result = prime * result + ((getSat() == null) ? 0 : getSat().hashCode());
        result = prime * result + ((getStateSat() == null) ? 0 : getStateSat().hashCode());
        result = prime * result + ((getSun() == null) ? 0 : getSun().hashCode());
        result = prime * result + ((getStateSun() == null) ? 0 : getStateSun().hashCode());
        result = prime * result + ((getPrizeCode() == null) ? 0 : getPrizeCode().hashCode());
        result = prime * result + ((getRetroactiveNum() == null) ? 0 : getRetroactiveNum().hashCode());
        result = prime * result + ((getRetroactiveTime() == null) ? 0 : getRetroactiveTime().hashCode());
        result = prime * result + ((getDrawtime() == null) ? 0 : getDrawtime().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", mon=").append(mon);
        sb.append(", stateMon=").append(stateMon);
        sb.append(", tue=").append(tue);
        sb.append(", stateTue=").append(stateTue);
        sb.append(", wed=").append(wed);
        sb.append(", stateWed=").append(stateWed);
        sb.append(", thur=").append(thur);
        sb.append(", stateThur=").append(stateThur);
        sb.append(", fri=").append(fri);
        sb.append(", stateFri=").append(stateFri);
        sb.append(", sat=").append(sat);
        sb.append(", stateSat=").append(stateSat);
        sb.append(", sun=").append(sun);
        sb.append(", stateSun=").append(stateSun);
        sb.append(", prizeCode=").append(prizeCode);
        sb.append(", retroactiveNum=").append(retroactiveNum);
        sb.append(", retroactiveTime=").append(retroactiveTime);
        sb.append(", drawtime=").append(drawtime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}