package com.shantery.result2;
import static com.shantery.result2.util.Result2Constants.*;

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
	private int recordPerPage; // 1ページあたりの表示件数
	@Value("${app.paginglength}")
	private int pagingLength; //ページの表示数
	@Autowired
	Result2Repository r2Repository; //呼び出すクラス

	/**
	 * 日付を昇順にソートした今いるページと開始ページを返すメソッド
	 * @param page 今いるページ
	 * @param recordPerPage	1ページ当たりの表示件数
	 * @return	r2RepositoryのfindAllOrderByDateのListをResult2Controllerに返す
	 */
	public List<Result2> find(String page, int recordPerPage) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページが返される。
		int offset = (currentPage - ONE); //開始ページの初期化
		return r2Repository.findAllOrderByDate(PageRequest.of(offset, recordPerPage)); //Result2Controllerに返す
	}

	/**
	 * ページングの機能を実装
	 * @param sWord	検索ワード
	 * @param page 今いるページ
	 * @return PagingUtilのgeneratePagingViewのページングの機能をResult2Controllerに返す
	 */
	public PagingView r2Paging(String sWord, String page) {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		int totalRecordNum = count(Result2Util.getSearchWord(sWord)); //検索ワードが返され、検索結果の総数が数えられる。
		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>()); //Result2Controllerに返す。
	}

	/**
	 * データを検索するメソッド
	 * @param sWord	検索ワード
	 * @param page 今いるページ
	 * @param recordPerPage	1ページ当たりの表示件数
	 * @return	r2Repositoryのsearchメソッドを呼び,ListをResult2Controllerに返す。
	 */
	public List<Result2> search(String sWord, String page, int recordPerPage) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - ONE); //開始ページの初期化
		return r2Repository.search(word, PageRequest.of(offset, recordPerPage)); //searchメソッドを呼び、検索ワードと開始ページ、1ページに表示するレコード数をr2Repositoryに返す。
	}

	/**
	 * データの総件数を返すメソッドをResult2Controllerに返す。
	 * @param sWord 検索ワード
	 * @return	r2RepositoryのcountAllメソッドを呼び、Result2Controllerにデータの総件数を返す。
	 */
	public int count(String sWord) {
		return r2Repository.countAll(sWord); //countAllメソッドを呼び、r2Repositoryにデータの総件数を返す。
	}
}