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
	HttpSession session;
	PagingUtil pu;

	/**
	 * 日付を昇順にソートした今いるページと開始ページを返すメソッド
	 * @param page 今いるページ
	 * @param recordPerPage	1ページ当たりの表示件数
	 * @return	r2RepositoryのfindAllOrderByDateのListをResult2Controllerに返す
	 */
	public List<Result2> find(String page, int recordPerPage) throws ParseException{
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページ数が返される。
		int offset = (currentPage - 1); //開始ページの初期化
		return r2Repository.findAllOrderByDate(PageRequest.of(offset, recordPerPage)); //Result2Controllerに返す
		}

	/**
	 * ページングの機能を実装
	 * @param sWord	検索ワード
	 * @param page 今いるページ
	 * @return PagingUtilのgeneratePagingViewのページングの機能をResult2Controllerに返す
	 */
	public PagingView r2Paging(String sWord,String page) {
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページ数が返される。
		int totalRecordNum = count(Result2Util.getSearchWord(sWord)); //検索ワードが返され、検索結果の総数が数えられる。
		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>());	//Result2Controllerに返す。
	}

	/**
	 * データを検索するメソッド
	 * @param sWord	検索ワード
	 * @param page 今いるページ
	 * @param recordPerPage	1ページ当たりの表示件数
	 * @return	r2Repositoryのsearchメソッドを呼び,ListをResult2Controllerに返す。
	 */
	public List<Result2> search(String sWord, String page, int recordPerPage) throws ParseException{
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage -1);	//開始ページの初期化
		return r2Repository.search(word,PageRequest.of(offset, recordPerPage));	//Result2Controllerに返す。
	}

	/**
	 * データの総件数を返すメソッドをResult2Controllerに返す。
	 * @param sWord 検索ワード
	 * @return	r2RepositoryのcountAllメソッドを呼び、Result2Controllerにデータの総件数を返す。
	 */
	public int count(String sWord) {
		return r2Repository.countAll(sWord);	//Result2Controllerに返す。
	}
}