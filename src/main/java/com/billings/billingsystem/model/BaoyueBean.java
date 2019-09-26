package com.billings.billingsystem.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
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
}