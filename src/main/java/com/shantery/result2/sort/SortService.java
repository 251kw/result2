package com.shantery.result2.sort;
import static com.shantery.result2.util.Result2Constants.*;

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
	SortRepository sRepository; //呼び出すクラス
	@Value("${app.recordperpage}")
	private int recordPerPage; // 1ページあたりの表示件数
	@Value("${app.paginglength}")
	private int pagingLength; // ページの表示数

	/**
	 * 日付昇順にソートした今いるページと開始ページを返す
	 * @param page 今いるページ
	 * @param recordPerPage 1ページ当たりの表示件数
	 * @return sRepositoryのfindAllOrderByDateで処理したListをSortControllerに返す
	 */
	public List<Result2> find(int page, int recordPerPage, String search_kbn) throws ParseException {
		int offset = page - ONE; // 開始位置
		return sRepository.findAllOrderByDate(search_kbn, PageRequest.of(offset, recordPerPage)); //SortControllerに返す。
	}

	/**
	 * 日付を昇順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord	検索ワード
	 * @return sRepositoryのfindAllOrderByDateASCで処理したListをSortControllerに返す
	 */
	public List<Result2> findAllOrderByDateASC(String page, String sWord, String search_kbn) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);	//getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - ONE);	//開始ページの初期化
		return sRepository.findAllOrderByDateASC(word, search_kbn,PageRequest.of(offset, recordPerPage)); //SortControllerに返す
	}

	/**
	 * 日付を降順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord	検索ワード
	 * @return sRepositoryのfindAllOrderByDateDESCで処理したListをSortControllerに返す
	 */
	public List<Result2> findAllOrderByDateDESC(String page, String sWord, String search_kbn) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - ONE); //開始ページの初期化
		return sRepository.findAllOrderByDateDESC(word, search_kbn, PageRequest.of(offset, recordPerPage)); //SortControllerに返す
	}

	/**
	 * 単金を昇順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord 検索ワード
	 * @return sRepositoryのfindAllOrderByCostASCで処理したListをSortControllerに返す
	 */
	public List<Result2> findAllOrderByCostASC(String page, String sWord, String search_kbn) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - ONE); //開始ページの初期化
		return sRepository.findAllOrderByCostASC(word, search_kbn,PageRequest.of(offset, recordPerPage)); //SortControllerに返す
	}

	/**
	 * 単金を降順にソートした処理を返す
	 * @param page 今いるページ
	 * @param sWord 検索ワード
	 * @return sRepositoryのfindAllOrderByCostDESCで処理したListをSortControllerに返す
	 */
	public List<Result2> findAllOrderByCostDESC(String page, String sWord, String search_kbn) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - ONE); //開始ページの初期化
		return sRepository.findAllOrderByCostDESC(word, search_kbn, PageRequest.of(offset, recordPerPage)); //SortControllerに返す
	}

	/**
	 * 年齢を昇順にソートした処理を返す
	 * @param page 今いるワード
	 * @param sWord 検索ワード
	 * @return sRepositoryのfindAllOrderByAgeASCで処理したListをSortControllerに返す
	 */
	public List<Result2> findAllOrderByAgeASC(String page, String sWord, String search_kbn) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - ONE); //開始ページの初期化
		return sRepository.findAllOrderByAgeASC(word, search_kbn, PageRequest.of(offset, recordPerPage)); //SortControllerに返す
	}

	/**
	 * 年齢を降順にソートした処理を返す
	 * @param page 今いるワード
	 * @param sWord 検索ワード
	 * @return sRepositoryのfindAllOrderByAgeDESCで処理したListをSortControllerに返す
	 */
	public List<Result2> findAllOrderByAgeDESC(String page, String sWord, String search_kbn) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		String word = Result2Util.getSearchWord(sWord); //getSearchWordメソッドを呼び検索ワードを渡す。
		int offset = (currentPage - ONE); //開始ページの初期化
		return sRepository.findAllOrderByAgeDESC(word, search_kbn, PageRequest.of(offset, recordPerPage));//SortControllerに返す
	}

	/**
	 * ページングの機能を実装
	 * @param page 今いるページ
	 * @param sWord 検索ワード
	 * @return PagingUtilのgeneratePagingViewのページングの機能をSortControllerに返す
	 */
	public PagingView Paging(String sWord, String page, String search_kbn) {
		int currentPage = Result2Util.getCurrentPage(page); //getCurrentPageメソッドを呼び、今いるページ数が返される。
		int totalRecordNum = count(Result2Util.getSearchWord(sWord), search_kbn); //検索ワードが返され、検索結果の総数が数えられる。
		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>()); //SortControllerに返す
	}

	/**
	 * データの総件数を返すメソッドをSortControllerに返す
	 * @param sWord 検索ワード
	 * @return sRepositoryのcountAllメソッドを呼び、SortControllerにデータの総件数を返す。
	 */
	public int count(String sWord, String search_kbn) {
		return sRepository.countAll(sWord, search_kbn); //SortControllerに返す
	}
}
