package com.shantery.result2.util;

import static com.shantery.result2.util.Result2Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

import com.shantery.result2.Result2;
import com.shantery.result2.paging.PagingView;

/**
 * Result2システムで使用する共通クラス
 * Result2固有の共通した処理はこのクラスに集約
 */
public class Result2Util {

	/** インスタンス生成禁止 **/
	private Result2Util() {}
	/** ページ番号の初期値 **/
	private static final String DEFAULT_PEGE = "1";
	/** 表の表示列数 **/
	@Value("${app.columnsize}")
	private static int columnSize;

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

	/**
	 * 表示する見出しを返却する
	 * 列数名はapplication.propertiesより取得
	 * @param columns 列名（当クラスで設定ファイルからインジェクションすることは不可の為、引数で受け取る）
	 * @return 表示する見出し
	 */
	public static List<String> getColumnName(String columns) {

		List<String> columnList   = new ArrayList<>();
		// プロパティファイルから取得した項目をカンマで分割
		String[]     columnArrays = columns.split(",");
		// 表示する件数分繰り返す
		Arrays.stream(columnArrays).forEach(columnList::add);
		// 表示する見出しを返却
		return columnList;
	}

	/**
	 * DBから取得したListの内容を変換する（Result2型->List型）
	 * 理由：Thymeleafで項目を可変にする為に実装
	 * @param list DBから取得したリスト
	 * @param columns 列名（当クラスで設定ファイルからインジェクションすることは不可の為、引数で受け取る）
	 * @return 変換後のリスト
	 */
	public static List<List<String>> convBeanToList(List<Result2> list, String columns) {

		List<List<String>> listResult2 = new ArrayList<>();
		// プロパティファイルから取得した項目をカンマで分割
		String[] columnArrays = columns.split(",");
		// Result2の内容をListに変換
		for (int i = 0; i < columnArrays.length; i++) {
			Result2 ret = list.get(i);
			listResult2.add(ret.getResult2List());
		}

		// 表示内容がすべて入ったリストを返却
		return listResult2;
	}
}