package com.shantery.result2.paging;

import java.util.List;
//import lombok.Data;

//@Data
public class PagingView {
	private int totalRecordNum;	//データの総件数
	private int fromRecordNum;	// ○件~×件の○の値
	private int toRecordNum;	// ○件~×件の×の値

	private int currentPageNum;	// 現在のページ番号
	private int totalPageNum; //総ページ数

	private int recordPerPage;	// 1ページ当たりの表示件数

	private boolean canGoNext;	// 次のページに行けるかどうか
	private String nextHref;	// 次のページ目のURLの文字列

	private boolean canGoPrevious;	// 前のページに行けるかどうか
	private String previousHref;	// 前のページ目のURLの文字列

	private boolean canGoLast;	// 終端に行けるかどうか
	private String lastHref;	// 終端のページのURLの文字列

	private boolean canGoFirst;	// 最初に行けるかどうか
	private String firstHref;	// 最初のページのURLの文字列

	private List<PagingViewElement> pagingViewElements;	// ページに表示されるデータ

	public int getTotalRecordNum() {
		return totalRecordNum;
	}

	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}

	public int getFromRecordNum() {
		return fromRecordNum;
	}

	public void setFromRecordNum(int fromRecordNum) {
		this.fromRecordNum = fromRecordNum;
	}

	public int getToRecordNum() {
		return toRecordNum;
	}

	public void setToRecordNum(int toRecordNum) {
		this.toRecordNum = toRecordNum;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public boolean isCanGoNext() {
		return canGoNext;
	}

	public void setCanGoNext(boolean canGoNext) {
		this.canGoNext = canGoNext;
	}

	public String getNextHref() {
		return nextHref;
	}

	public void setNextHref(String nextHref) {
		this.nextHref = nextHref;
	}

	public boolean isCanGoPrevious() {
		return canGoPrevious;
	}

	public void setCanGoPrevious(boolean canGoPrevious) {
		this.canGoPrevious = canGoPrevious;
	}

	public String getPreviousHref() {
		return previousHref;
	}

	public void setPreviousHref(String previousHref) {
		this.previousHref = previousHref;
	}

	public boolean isCanGoLast() {
		return canGoLast;
	}

	public void setCanGoLast(boolean canGoLast) {
		this.canGoLast = canGoLast;
	}

	public String getLastHref() {
		return lastHref;
	}

	public void setLastHref(String lastHref) {
		this.lastHref = lastHref;
	}

	public boolean isCanGoFirst() {
		return canGoFirst;
	}

	public void setCanGoFirst(boolean canGoFirst) {
		this.canGoFirst = canGoFirst;
	}

	public String getFirstHref() {
		return firstHref;
	}

	public void setFirstHref(String firstHref) {
		this.firstHref = firstHref;
	}

	public List<PagingViewElement> getPagingViewElements() {
		return pagingViewElements;
	}

	public void setPagingViewElements(List<PagingViewElement> pagingViewElements) {
		this.pagingViewElements = pagingViewElements;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

}
