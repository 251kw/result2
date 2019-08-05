package com.shantery.result2.util;

/**
 * @author r.kawahara
 * 共通　定数を保持するクラス
 * 共通して使用する値はこのクラスで定義する。
 */
public class Result2Constants {

	/** インスタンス生成禁止 **/
	private Result2Constants() {}

	// URLのパラメータを示す変数
	/** 起動時やTOPページに戻るときに使う変数 **/
	public static final String TOP = "/";
	/** 本文詳細ボタンを押したときに使う変数 **/
	public static final String FROM_TEXT_DETAILS_BUTTON = "/result";
	/** 本文を表示しているページの戻るボタンを押したときに使う変数 **/
	public static final String FROM_BACK_BUTTON = "/return";
	/** 検索ボタンが押されたときに使う変数 **/
	public static final String FROM_SEARCH_BUTTON = "/sresults";

	// 遷移先を示す変数
	/** TOPページの遷移先 **/
	public static final String TO_TOP = "index";
	/** 本文詳細ページの遷移先 **/
	public static final String TO_TEXT_DETAILS = "display";
	/** 検索結果ページの遷移先 **/
	public static final String TO_SEARCH_RESULTS = "seachResults";

	// キーを示す変数
	/** セッションのキー **/
	public static final String SESSION_FORM_ID = "searchForm";
	/** ページング機能のキー **/
	public static final String PAGING = "paging";
	/** データベースからとってきたリストのキー **/
	public static final String LIST = "test";
	/** 検索ワードを元にデータベースからとってきたリストのキー **/
	public static final String SEARCH_LIST = "sResults";
	/** 本文のキー **/
	public static final String TEXT = "text";
	/** 何ページ目かを保存するキー **/
	public static final String KEEP_SET_PAGE = "page";

	// @RequestParamのname属性
	/** 検索ワードが入っているname属性 **/
	public static final String SEARCH_WORD = "sWord";
	/** 本文が入っているname属性 **/
	public static final String HONBUN = "hidden";
	/** 本文詳細ページに飛ぶときに何ページ目にいたかが入っているname属性 **/
	public static final String KEEP_GET_PAGE = "fromdisplay";

	// その他よく使う文字の変数
	/** LIKE検索用の最初の%、外部化済みなので削除推奨 **/
	public static final String TOP_PERCENT = "'%";
	/** LIKE検索用の最後の%、外部化済みなので削除推奨 **/
	public static final String END_PERCENT = "%'";
	/** 空文字 **/
	public static final String EMPTY = "";
}
