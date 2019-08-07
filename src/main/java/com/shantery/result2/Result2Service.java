package com.shantery.result2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shantery.result2.paging.PagingUtil;
import com.shantery.result2.paging.PagingView;
import com.shantery.result2.util.Result2Util;

@Service
public class Result2Service {

	@Value("${app.recordperpage}")
    private int recordPerPage;	// 1ページあたりの表示件数
	@Value("${app.paginglength}")	// << < (1 2 3 4 5)←これの表示数 > >>
	private int pagingLength;
	//private static final String SESSION_FORM_ID="searchForm";	// キー

	@Autowired
	Result2Repository r2Repository;

	public List<Result2> find(String page, int recordPerPage) throws ParseException{
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページが返される。
		int offset = (currentPage - 1); //開始ページの初期化
		return r2Repository.findAllOrderByDate(PageRequest.of(offset, recordPerPage)); //Result2Repositoryに返す
		}

	public PagingView r2Paging(String sWord,String page) {
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページが返される。
		int totalRecordNum = count(Result2Util.getSearchWord(sWord));
		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>());
	}

	//データを検索するメソッド
	public List<Result2> search(String sWord, String page, int recordPerPage) throws ParseException{
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページが返される。
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage -1);
		return r2Repository.search(word,PageRequest.of(offset, recordPerPage));
	}

	// データの総件数を返すメソッド
	public int count(String sWord) {
		return r2Repository.countAll(sWord);
	}
}