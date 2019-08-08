package com.shantery.result2.util;

import static com.shantery.result2.util.Result2Constants.*;

import java.util.Optional;

import com.shantery.result2.paging.PagingView;

public class Result2Util {

	/*@Autowired
	PagingView pv;*/
	/** インスタンス生成禁止 **/
	private Result2Util() {}
	/** ページ番号の初期値 **/
	private static final String DEFAULT_PEGE = "1";

	/**
	 * 現在のページを返却します。
	 * @param page 遷移先のページ番号
	 * @return 現在ページ
	 */
	public static int getCurrentPage(String page) {
		// 現在いるページ番号の初期化
		var tmpPage = Optional.ofNullable(page).orElse(DEFAULT_PEGE);
		return Integer.parseInt(tmpPage);
	}

	public static int getCurrentPageForDatabase(String page) {
		// 現在いるページ番号の初期化
		var tmpPage = Optional.ofNullable(page).orElse(DEFAULT_PEGE);
		int tpn = PagingView.getTotalPageNum();
		if(tpn < Integer.parseInt(tmpPage) || Integer.parseInt(tmpPage) < 1) {
			tmpPage = "1";
		}
		return Integer.parseInt(tmpPage);
	}

	/**
	 * 検索ワードを返すメソッド
	 * もしnullだったら空文字が入るようにする
	 * @param word 検索ワード
	 * @return 検索ワード
	 */
	public static String getSearchWord(String word) {
		// 検索ワードの初期化
		var tmpWord = Optional.ofNullable(word).orElse(EMPTY);
		return tmpWord;
	}
}
