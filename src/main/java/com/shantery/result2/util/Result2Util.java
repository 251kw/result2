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
	private Result2Util() {
	}
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
		// 空白削除
		tmpPage = tmpPage.replaceFirst("^[\\h]+", "").replaceFirst("[\\h]+$", "");
		// 小数点以下切り捨て
		String tmp = EMPTY;
		// 小数点があるかどうかのflag
		boolean flag = false;
		// for文を数える変数
		int count = 0;
		// 小数点を探すための繰り返し文
		for (int i = 0; i < tmpPage.length(); i++) {
			// もし小数点が見つかったならflagを立ててこのループ文を抜け出す
			if ('.' == tmpPage.charAt(i) || '．' == tmpPage.charAt(i)) {
				flag = true;
				break;
			} else { // そうじゃなければtmpに文字を追加する
				count++;
				// countが10になった時、これ以上はNumberExceptionが起きる可能性があるのでループを抜け出す
				if(count > 9) {
					break;
				}
				tmp += tmpPage.charAt(i);
			}
		}
		// もし小数点が見つかっていたらtmpPageをtmpに変える
		if (flag) {
			tmpPage = tmp;
		}
		// 文字列が大きすぎる場合1ページ目にする。
		if (tmpPage.length() > 9 || tmpPage.length() < 1) {
			tmpPage = DEFAULT_PEGE;
		}
		// 全角数字を半角数字に直す
		tmpPage = zenNumToHanNum(tmpPage);
		//ページの総数
		int tpn = PagingView.getTotalPageNum();
		//もし現在いるページ番号がページの総数より小さいまたは、1より小さい時は1ページ目を返す
		try {
			if (tpn < Integer.parseInt(tmpPage) || Integer.parseInt(tmpPage) < 1) {
				tmpPage = DEFAULT_PEGE;
			}
			return Integer.parseInt(tmpPage);
		} catch (Exception e) { // 数字以外の文字が入っていたらキャッチして1ページ目を返す。
			return Integer.parseInt(DEFAULT_PEGE);
		}
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

		// プロパティファイルから取得した項目をカンマで分割
		String[] columnArrays = columns.split(",");
		// 表示する見出しリスト
		List<String> columnList = new ArrayList<>();
		// 表示する件数分繰り返す
		Arrays.stream(columnArrays).forEach(columnList::add);
		// 表示する見出しを返却
		return columnList;
	}

	/**
	 * 表示する見出しの数を返却する
	 * 列数名はapplication.propertiesより取得
	 * @param columns 列名（当クラスで設定ファイルからインジェクションすることは不可の為、引数で受け取る）
	 * @return 表示する見出しの個数
	 */
	public static int getColumnCount(String columns) {

		// プロパティファイルから取得した項目をカンマで分割
		String[] columnArrays = columns.split(",");
		// 表示する見出しの数を返却
		return columnArrays.length;
	}

	/**
	 * DBから取得したListの内容を変換する（Result2型->List型）
	 * 理由：Thymeleafで項目を可変にする為に実装
	 * @param list DBから取得したリスト
	 * @return 変換後のリスト
	 */
	public static List<List<String>> convBeanToList(List<Result2> list) {

		// Result2の内容をListに変換
		List<List<String>> listResult2 = new ArrayList<>();
		list.stream().map(Result2::getResult2List).forEach(listResult2::add);

		// 表示内容がすべて入ったリストを返却
		return listResult2;
	}

	/**
	 * 全角数字を半角数字に直すメソッド
	 * @param num pageの文字列
	 * @return 全角数字を半角数字に直した文字列
	 */
	private static String zenNumToHanNum(String num) {
		StringBuffer sb = new StringBuffer(num);
		for(int i = 0; i < num.length(); i++) {
			char c = sb.charAt(i);
			if('０' <= c && c <= '９') {
				sb.setCharAt(i, (char)(c - '０' + '0'));
			}
		}
		return sb.toString();
	}
}