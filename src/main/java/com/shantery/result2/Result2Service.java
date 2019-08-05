package com.shantery.result2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shantery.result2.paging.PagingUtil;
import com.shantery.result2.paging.PagingView;

@Service
public class Result2Service {

	@Value("${app.recordperpage}")
    private int recordPerPage;	// 1ページあたりの表示件数
	@Value("${app.paginglength}")	// << < (1 2 3 4 5)←これの表示数 > >>
	private int pagingLength;
	//private static final String SESSION_FORM_ID="searchForm";	// キー
	@Autowired
	Result2Repository r2Repository;
	HttpSession session;
	PagingUtil pu;

	public List<Result2> find(String page, int recordPerPage) throws ParseException{

		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int offset = currentPage - 1; // 開始位置

		return r2Repository.findAllOrderByDate(PageRequest.of(offset, recordPerPage));
		}

	public PagingView r2Paging(String sWord,String page) {
		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		//int offset = currentPage - 1; // 開始位置
		int totalRecordNum = count(sWord);

		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>());
	}/*
	public int count(String sWord) {	// データの総件数を返すメソッド
		return r2Repository.countAll(sWord);
	}*/

	public List<Result2> search(String sWord, String page, int recordPerPage) throws ParseException{
		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		//int totalRecordNum;	// データの総件数を取るための変数
		//boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
			//flag = true;	// フラグを立てる
			//int totalRecordNum = count2(sWord);	// 検索してヒットしたデータの総件数を取る
		int offset = (currentPage-1);
		return r2Repository.search(sWord,PageRequest.of(offset, recordPerPage));
	}

	public int count(String sWord) {	// データの総件数を返すメソッド
		return r2Repository.countAll(sWord);
	}
}