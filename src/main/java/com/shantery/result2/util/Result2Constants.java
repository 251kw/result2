package com.shantery.result2.util;

/**
 * @author r.kawahara
 * 共通　定数を保持するクラス
 * 共通して使用する値はこのクラスで定義する。
 */
public class Result2Constants {

	/** インスタンス生成禁止 **/
	private Result2Constants() {
	}

	// URLのパラメータを示す変数
	/** 起動時やTOPページに戻るときに使う変数 **/
	public static final String TOP = "/";
	/** 本文詳細ボタンを押したときに使う変数 **/
	public static final String FROM_TEXT_DETAILS_BUTTON = "/result";
	/** 本文を表示しているページの戻るボタンを押したときに使う変数 **/
	public static final String FROM_BACK_BUTTON = "/return";
	/** 検索ボタンが押されたときに使う変数 **/
	public static final String FROM_SEARCH_BUTTON = "/sresults";
	/** 日付昇順ボタンが押されたとき **/
	public static final String FROM_DATEASC_BUTTON = "/ascdate";
	/** 日付降順ボタンが押されたとき **/
	public static final String FROM_DATEDESC_BUTTON = "/descdate";
	/** 単価昇順ボタンが押されたとき **/
	public static final String FROM_COSTASC_BUTTON = "/asccost";
	/** 単価降順ボタンが押されたとき **/
	public static final String FROM_COSTDESC_BUTTON = "/desccost";
	/** 年齢昇順ボタンが押されたとき **/
	public static final String FROM_AGEASC_BUTTON = "/ascage";
	/** 年齢降順ボタンが押されたとき **/
	public static final String FROM_AGEDESC_BUTTON = "/descage";
	/** 人材情報が押されたとき **/
	public static final String FROM_HUMAMRESOURCE_BUTTON = "/humanresource";
	/** 案件情報が押されたとき **/
	public static final String FROM_WORK_BUTTON = "/work";

	// 遷移先を示す変数
	/** TOPページの遷移先 **/
	public static final String TO_TOP = "index";
	/** 本文詳細ページの遷移先 **/
	public static final String TO_TEXT_DETAILS = "display";
	/** 検索結果ページの遷移先 **/
	public static final String TO_SEARCH_RESULTS = "searchResults";

	// キーを示す変数
	/** セッションのキー **/
	public static final String SESSION_FORM_ID = "searchForm";
	/** 人材か案件かを保存するセッションのキー **/
	public static final String SESSION_FORM_KEY = "human_resource_or_work";
	/** ページング機能のキー **/
	public static final String PAGING = "paging";
	/** データベースからとってきたリストのキー **/
	public static final String LIST = "result2_list";
	/** 検索ワードを元にデータベースからとってきたリストのキー **/
	public static final String SEARCH_LIST = "sResults";
	/** 本文のキー **/
	public static final String TEXT = "text";
	/** 何ページ目かを保存するキー **/
	public static final String KEEP_SET_PAGE = "page";
	/** 表題を保存するキー **/
	public static final String COLUMN_HEAD = "head";
	/** 表の長さを保存するキー **/
	public static final String COLUMN_LENGTH = "columnlength";

	// @RequestParamのname属性
	/** 検索ワードが入っているname属性 **/
	public static final String SEARCH_WORD = "sWord";
	/** 本文が入っているname属性 **/
	public static final String HONBUN = "honbun";
	/** 本文詳細ページに飛ぶときに何ページ目にいたかが入っているname属性 **/
	public static final String KEEP_GET_PAGE = "fromdisplay";

	// その他よく使う文字の変数
	/** LIKE検索用の最初の%、外部化済みなので削除推奨 **/
	public static final String TOP_PERCENT = "'%";
	/** LIKE検索用の最後の%、外部化済みなので削除推奨 **/
	public static final String END_PERCENT = "%'";
	/** 空文字 **/
	public static final String EMPTY = "";
	/** ページングに使う 0 **/
	public static final int ZERO = 0;
	/** ページングに使う 1 **/
	public static final int ONE = 1;
	/** ページングに使う 2 **/
	public static final int TWO = 2;
	/** ページングに使う 9 **/
	public static final int NINE = 9;
	/**URLの後ろの&page**/
	public static final String PREPAGE = "&page=";
	/**クエリ文字**/
	public static final String Query = "?";
	/**＆**/
	public static final String AND = "&";
	/**カンマ**/
	public static final String COMMA = ",";
	/** 空白で始まる正規表現 **/
	public static final String STARTEMPTY = "^[\\h]+";
	/** 空白で終わる正規表現 **/
	public static final String FINISHEMPTY ="[\\h]+$";
	/** ページ番号の初期値 **/
	public static final String DEFAULT_PEGE = "1";


	//全角文字から半角文字に直すメソッドの変数
	/** 全角0 **/
	public static final char FULLWIDTHZERO = '０';
	/** 全角9 **/
	public static final char FULLWIDTHNINE = '９';
	/** 半角0 **/
	public static final char HALFSIZEZERO = '0';

	// 区分
	/** 人材情報 **/
	public static final String KBN_SEARCH_HUMANRESOURCE = "1";
	/** 案件情報 **/
	public static final String KBN_SEARCH_WORK          = "2";
}
