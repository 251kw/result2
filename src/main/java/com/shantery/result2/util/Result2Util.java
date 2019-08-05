package com.shantery.result2.util;

import java.util.Optional;

public class Result2Util {

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
		// pageが存在する
		var tmpPage = Optional.ofNullable(page).orElse(DEFAULT_PEGE);
		return Integer.parseInt(tmpPage);
	}
}
