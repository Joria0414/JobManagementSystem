package com.ics.employeemanager.utils;

import java.util.List;


/*
 * 分页工具类
 * 泛型的使用，确保可以给任何东西分类
 */
public class ResultPage<T> {
	private int currentPage;//当前页
	private int totalPage;//总页数
	private int totalCount;//总条数
	private List<T> lists;//泛型类,当前页的数据列表
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	@Override
	public String toString() {
		return "ResultPage [currentPage=" + currentPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ ", lists=" + lists + "]";
	}
	
	

}
