/**
 * @Title Page.java。
 * @Description 分页模型
 * @author hanmeng
 * @date 2014年5月5日
 * @version V1.0
 */

package com.billings.billingsystem.common;

import java.util.List;

/**
 * @param <T> page中存放的数据的类型
 * @author xusheng
 * @since 2014年1月28日 下午3:36:31
 */
public class Page<T> {

    /**
     * @Fields pageIndex : 页码
     */
    private int pageIndex;

    /**
     * @Fields totalCount : 总个数
     */
    private int totalCount;

    /**
     * @Fields perPageCount : 每页个数
     */
    private int perPageCount;

    /**
     * @Fields result : 结果列表
     */
    private List<T> result;

    /**
     * @Fields searchName : 搜索名称
     */
    private String searchName;

    /**获取搜索名称.
     * @return String
     * @create: 2014年5月15日 上午8:59:19 hanmeng
     * @history:
     */
    public String getSearchName() {
        return searchName;
    }

    /**设置搜索名称.
     * @param name 搜索名称
     * @create: 2014年5月15日 上午8:59:41 hanmeng
     * @history:
     */
    public void setSearchName(String name) {
        this.searchName = name;
    }

    /**
     * 通过页码，总条数，每页个数构造分页模型.
     * @param pIndex 页码
     * @param total 总条数
     * @param perPage 每页个数
     * @create: 14-2-13 下午2:54 xusheng
     * @history:
     */
    public Page(int pIndex, int total, int perPage) {
        this.pageIndex = pIndex;
        this.totalCount = total;
        this.perPageCount = perPage;
    }

    /**
     * @param pIndex 页码
     * @param total 总条数
     * @create: 14-2-13 下午2:54 xusheng
     * @history:
     */
    public Page(int pIndex, int total) {
        this(pIndex, total, Constant.PER_PAGE_COUNT);
    }

    /**
     * @param pIndex 页码
     * @create: 14-2-13 下午2:54 xusheng
     * @history:
     */
    public Page(int pIndex) {
        this(pIndex, 0);
    }

    /**
     * @create: 14-2-13 下午2:54 xusheng
     * @history:
     */
    public Page() {
        this(1);
    }

    /**
     * 获取页码.
     * @return 页码
     * @create: 14-2-13 下午3:02 xusheng
     * @history:
     */
    public int getPageIndex() {
        int pageCount = getPageCount();
        if (pageIndex <= pageCount) {
            return pageIndex;
        } else {
            return pageCount;
        }
    }

    /**
     * 设置页码.
     * @param pIndex 页码
     * @create: 14-2-13 下午3:03 xusheng
     * @history:
     */
    public void setPageIndex(int pIndex) {
        this.pageIndex = pIndex;
    }

    /**
     * 获取总条数.
     * @return 总条数
     * @create: 14-2-13 下午3:03 xusheng
     * @history:
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总条数.
     * @param total 总条数
     * @create: 14-2-13 下午3:04 xusheng
     * @history:
     */
    public void setTotalCount(int total) {
        this.totalCount = total;
    }

    /**
     * 获取每页个数.
     * @return 每页个数
     * @create: 14-2-13 下午3:04 xusheng
     * @history:
     */
    public int getPerPageCount() {
        return perPageCount;
    }

    /**
     * 设置每页条数.
     * @param perPage 每页个数
     * @create: 14-2-13 下午3:05 xusheng
     * @history:
     */
    public void setPerPageCount(int perPage) {
        this.perPageCount = perPage;
    }

    /**
     * 获取结果列表.
     * @return 结果列表
     * @create: 14-2-13 下午3:05 xusheng
     * @history:
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * 设置结果列表.
     * @param resultList 结果列表
     * @create: 14-2-13 下午3:06 xusheng
     * @history:
     */
    public void setResult(List<T> resultList) {
        this.result = resultList;
    }

    /**
     * 获取总页数.
     * @return 总页数
     * @create: 14-2-13 下午3:06 xusheng
     * @history:
     */
    public int getPageCount() {
        int pageCount = (int) Math.ceil((float) totalCount / perPageCount);
        if (pageCount == 0) {
            return 1;
        } else {
            return pageCount;
        }

    }

    /**获取当前页之前(不包括当前页)所有的数据的总数.
     * @return int
     * @create: 2014年5月8日 下午1:54:36 hanmeng
     * @history:
     */
    public int getStatisticsNumber() {

        if (0 == this.getPageIndex()) {
            return 0;
        } else {
            return perPageCount * (this.getPageIndex() - 1);
        }
    }
}
