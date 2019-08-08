package com.shantery.result2.sort;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shantery.result2.Result2;
import com.shantery.result2.paging.PagingUtil;
import com.shantery.result2.paging.PagingView;
import com.shantery.result2.util.Result2Util;

@Service
class SortService {
	@Autowired
	SortRepository sRepository;

	@Value("${app.recordperpage}")
	private int recordPerPage; // 1ページあたりの表示件数
	@Value("${app.paginglength}") // << < (1 2 3 4 5)←これの表示数 > >>
	private int pagingLength;

	/**
	 * 日付昇順にソートした今いるページと開始ページを返す
	 * @param page 今いるページ
	 * @param recordPerPage 1ページ当たりの表示件数
	 * @return sRepository
	 */
	public List<Result2> find(int page, int recordPerPage) throws ParseException {
		int offset = page - 1; // 開始位置
		return sRepository.findAllOrderByDate(PageRequest.of(offset, recordPerPage));//sRepositoryに返す
	}

	/**
	 * 日付を昇順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord	検索ワード
	 * @return	sRepository
	 */
	public List<Result2> findAllOrderByDateASC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - 1);	//開始ページの初期化
		return sRepository.findAllOrderByDateASC(word, PageRequest.of(offset, recordPerPage)); //sRepositoryに返す
	}

	/**
	 * 日付を降順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord	検索ワード
	 * @return sRepository
	 */
	public List<Result2> findAllOrderByDateDESC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);//getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - 1);//開始ページの初期化
		return sRepository.findAllOrderByDateDESC(word, PageRequest.of(offset, recordPerPage));//sRepositoryに返す
	}

	/**
	 * 単金を昇順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord 検索ワード
	 * @return sRepository
	 */
	public List<Result2> findAllOrderByCostASC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);//getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - 1);//開始ページの初期化
		return sRepository.findAllOrderByCostASC(word, PageRequest.of(offset, recordPerPage));//sRepositoryに返す
	}

	/**
	 * 単金を降順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord 検索ワード
	 * @return sRepository
	 */
	public List<Result2> findAllOrderByCostDESC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);//getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - 1);//開始ページの初期化
		return sRepository.findAllOrderByCostDESC(word, PageRequest.of(offset, recordPerPage));//sRepositoryに返す
	}

	/**
	 * 年齢を昇順にソートした処理を返す
	 * @param page 今いるワード
	 * @param sWord 検索ワード
	 * @return sRepository
	 */
	public List<Result2> findAllOrderByAgeASC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);//getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - 1);//開始ページの初期化
		return sRepository.findAllOrderByAgeASC(word, PageRequest.of(offset, recordPerPage));//sRepositoryに返す
	}

	/**
	 * 年齢を降順にソートした処理を返す
	 * @param page 今いるワード
	 * @param sWord 検索ワード
	 * @return sRepository
	 */
	public List<Result2> findAllOrderByAgeDESC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord);//getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - 1);//開始ページの初期化
		return sRepository.findAllOrderByAgeDESC(word, PageRequest.of(offset, recordPerPage));//sRepositoryに返す
	}

	/**
	 * ページングの機能を実装
	 * @param sWord 検索ワード
	 * @param page
	 * @return PagingUti
	 */
	public PagingView Paging(String sWord,String page) {
		int currentPage = Result2Util.getCurrentPage(page);//getCurrentPageメソッドを呼び、今いるページ数が返される。
		int totalRecordNum = count(Result2Util.getSearchWord(sWord));//検索ワードが返され、検索結果の総数が数えられる。
		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>());
	}

	/**
	 * データの総件数を返すメソッド
	 * @param sWord 検索ワード
	 * @return sRepository
	 */
	public int count(String sWord) {
		return sRepository.countAll(sWord);//sRepositoryにデータの総件数を返す
	}
}
