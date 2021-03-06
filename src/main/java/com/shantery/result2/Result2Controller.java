package com.shantery.result2;

import static com.shantery.result2.util.Result2Constants.*;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shantery.result2.util.Result2Util;

@Controller
class Result2Controller {

	@Value("${app.recordperpage}")
	private int recordPerPage; // 1ページあたりの表示件数

	@Autowired
	private Result2Service r2Service; //呼び出すクラス
	@Autowired
	HttpSession session; //呼び出すクラス

	@Value("${app.columns}")
	private String columns; // 1ページあたりの表示件数

	/**
	 * アプリケーションを起動させたとき、もしくは会社のロゴが押されたときに動く
	 * @param page 今いるページ
	 * @parem model 利用するデータを管理するためのクラス
	 * @return r2Serviceのfindで処理したLIST、r2Serviceのr2Pagingで処理したページング機能をindexに返す。
	 */
	@RequestMapping(value = TOP, method = RequestMethod.GET) // アプリケーションを起動させたとき、もしくは会社のロゴが押されたとき
	public String index(@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (session.getAttribute(SESSION_FORM_ID) != null) { // もしsessionスコープ内にデータがあるなら削除する
			session.removeAttribute(SESSION_FORM_ID);
		}
		session.setAttribute(SESSION_FORM_KEY, KBN_SEARCH_HUMANRESOURCE);
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		model.addAttribute(
				PAGING, r2Service.r2Paging(EMPTY, page, KBN_SEARCH_HUMANRESOURCE));
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		model.addAttribute(LIST,
				Result2Util.convBeanToList(r2Service.find(page, recordPerPage, KBN_SEARCH_HUMANRESOURCE))); // ServiceでSQL文の実行している
		return TO_TOP;
	}

	/**
	 * 詳細ボタンが押されたとき
	 * @param honbun メールの本文
	 * @param page 今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return メールの本文、ページ番号を覚えておくためのpageをdisplayに返す。
	 */
	@RequestMapping(value = FROM_TEXT_DETAILS_BUTTON, method = RequestMethod.POST) // 本文詳細ボタンが押されたとき
	public String postdisplay(@RequestParam(name = HONBUN) String honbun, Model model) {
		model.addAttribute(TEXT, honbun); // メールの本文をキーをtextでセットする
		return TO_TEXT_DETAILS;
	}

	/**
	 * フリーワードの検索ボタンが押されたとき
	 * @param sWord 検索ワード
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return r2Serviceのsearchで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_SEARCH_BUTTON, method = RequestMethod.POST) // フリーワードの検索ボタンが押されたとき
	public String postsearchResults(@RequestParam(name = SEARCH_WORD, required = false) String sWord,
			@RequestParam(required = false) final String page, Model model) throws ParseException {
		session.setAttribute(SESSION_FORM_ID, sWord); // 検索ワードをsessionスコープに保持
		// 人材情報か案件情報かを調べるためのセッション
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY);
		//ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord, page, search_kbn));
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		//Thymeleafで項目を可変にする為に変換し、変換後のリストを返す
		model.addAttribute(SEARCH_LIST,
				Result2Util.convBeanToList(r2Service.search(sWord, page, recordPerPage, search_kbn)));
		model.addAttribute(SEARCH_WORD, sWord);
		return TO_SEARCH_RESULTS; // searchResultsに返す
	}

	/**
	 * 文字を検索し、そのページをページングしたとき
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return r2Serviceのsearchで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_SEARCH_BUTTON, method = RequestMethod.GET) // 検索した結果のページでページングを行うとき
	public String getsearchResults(@RequestParam(required = false) final String page, Model model)
			throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); // 保持した検索ワードを取ってくる
		// 人材情報か案件情報かを調べるためのセッション
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY);
		//ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord, page, search_kbn));
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));//表の長さをキーとして、表示する見出しの数を返す
		//Thymeleafで項目を可変にする為に変換し、変換後のリストを返す
		model.addAttribute(SEARCH_LIST,
				Result2Util.convBeanToList(r2Service.search(sWord, page, recordPerPage, search_kbn)));
		model.addAttribute(SEARCH_WORD, sWord);
		return TO_SEARCH_RESULTS; // searchResultsに返す
	}

	/**
	 * 人材情報が押されたとき、また人材情報が押されてページングするとき
	 * @param page 今いるページ
	 * @param model データを管理するためのクラス
	 * @return　r2Serviceのfindで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をtopに返す。
	 * @throws ParseException
	 */
	@RequestMapping(value = FROM_HUMAMRESOURCE_BUTTON, method = RequestMethod.GET) // アプリケーションを起動させたとき、もしくは会社のロゴが押されたとき
	public String fromHumanResource(@RequestParam(required = false) final String page, Model model)
			throws ParseException {
		session.setAttribute(SESSION_FORM_KEY, KBN_SEARCH_HUMANRESOURCE);
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		model.addAttribute(
				PAGING, r2Service.r2Paging(EMPTY, page, KBN_SEARCH_HUMANRESOURCE));
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		model.addAttribute(LIST,
				Result2Util.convBeanToList(r2Service.find(page, recordPerPage, KBN_SEARCH_HUMANRESOURCE))); // ServiceでSQL文の実行している
		return TO_TOP;
	}

	/**
	 * 案件情報が押されたとき、また案件情報が押されてページングするとき
	 * @param page 今いるページ
	 * @param model データを管理するためのクラス
	 * @return r2Serviceのfindで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をtopに返す。
	 * @throws ParseException
	 */
	@RequestMapping(value = FROM_WORK_BUTTON, method = RequestMethod.GET) // アプリケーションを起動させたとき、もしくは会社のロゴが押されたとき
	public String fromWork(@RequestParam(required = false) final String page, Model model) throws ParseException {
		session.setAttribute(SESSION_FORM_KEY, KBN_SEARCH_WORK);
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		model.addAttribute(
				PAGING, r2Service.r2Paging(EMPTY, page, KBN_SEARCH_WORK));
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		model.addAttribute(LIST, Result2Util.convBeanToList(r2Service.find(page, recordPerPage, KBN_SEARCH_WORK))); // ServiceでSQL文の実行している
		return TO_TOP;
	}
}
