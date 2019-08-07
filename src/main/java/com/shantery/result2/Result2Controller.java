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

@Controller
class Result2Controller {

	@Value("${app.recordperpage}")
	private int recordPerPage;	// 1ページあたりの表示件数

	@Autowired
	private Result2Service r2Service;
	@Autowired
	HttpSession session;

	/**
	 * アプリケーションを起動させたとき、もしくは会社のロゴが押されたときに動く
	 * @param page 今いるページ
	 * @return r2Serviceのfindで処理したLIST、r2Serviceのr2Pagingで処理したページング機能をindexに返す。
	 */
	@RequestMapping(value = TOP, method = RequestMethod.GET)	// アプリケーションを起動させたとき、もしくは会社のロゴが押されたとき
	public String index(@RequestParam(required = false) final String page, Model model) throws ParseException {
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// もしsessionスコープ内にデータがあるなら削除する
			session.removeAttribute(SESSION_FORM_ID);
		}
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		model.addAttribute(
				PAGING,r2Service.r2Paging(EMPTY,page));
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute(LIST, r2Service.find(page, recordPerPage));	// ServiceでSQL文の実行している
		return TO_TOP;
	}

	/**
	 * 詳細ボタンが押されたとき
	 * @param honbun メールの本文
	 * @param page 今いるページ
	 * @return メールの本文、ページ番号を覚えておくためのpageをdisplayに返す。
	 */
	@RequestMapping(value = FROM_TEXT_DETAILS_BUTTON, method = RequestMethod.POST)	// 本文詳細ボタンが押されたとき
	public String postdisplay(@RequestParam(name = HONBUN) String honbun, @RequestParam(name = KEEP_SET_PAGE) String page, Model model){
		model.addAttribute(TEXT, honbun);	// メールの本文をキーをtextでセットする
		model.addAttribute(KEEP_SET_PAGE, page);	// 現在のページ番号を覚えておくためにpageをセットする
		return TO_TEXT_DETAILS;
	}

	/**
	 *displayのページから戻るとき
	 * @param page 今いるページ
	 * @param model
	 * @return	検索を行っていなければr2Serviceのfindで処理したLIST、r2Serviceのr2Pagingで処理したページング機能をindexに返す。
	 * @return	検索を行っていればr2Serviceのsearchで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をsearcResultsに返す。
	 */
	@RequestMapping(value = FROM_BACK_BUTTON, method = RequestMethod.POST)	// displayから戻るとき
	public String postbackIndex(@RequestParam(name = KEEP_GET_PAGE) final String page, Model model) throws ParseException {
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);
		 //ページングの機能としてキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			 //sResultsをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, r2Service.search(sWord, page, recordPerPage));
		} else {	// 検索を行っていないのなら
			 //testをキーとしてvalueをList型にしたものを返す
		model.addAttribute(LIST, r2Service.find(page, recordPerPage));
		}
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
			return TO_TOP;
		} else {	// そうでなければsearchResultsに戻す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 *displayから戻ってからページング
	 * @param page	今いるページ
	 * @return	検索を行っていなければr2Serviceのfindで処理したLIST、r2Serviceのr2Pagingで処理したページング機能をindexに返す。
	 * @return	検索を行っていればr2Serviceのsearchで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をsearcResultsに返す。
	 */
	@RequestMapping(value = FROM_BACK_BUTTON, method = RequestMethod.GET)	// displayから戻ってページングを行うとき
	public String getbackIndex(@RequestParam(required = false) final String page, Model model) throws ParseException {
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		 //ページングの機能としてキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			 //sResultsをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, r2Service.search(sWord,page, recordPerPage));
		} else {	// 検索を行っていないのなら
			 //testをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, r2Service.find(page, recordPerPage));
		}
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
			return TO_TOP;
		} else {	// そうでなければsearchResultsに戻す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * フリーワードの検索ボタンが押されたとき
	 * @param sWord 検索ワード
	 * @param page	今いるページ
	 * @param model
	 * @return r2Serviceのsearchで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_SEARCH_BUTTON, method = RequestMethod.POST)	// フリーワードの検索ボタンが押されたとき
	public String postsearchResults(@RequestParam(name = SEARCH_WORD,required = false) String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		session.setAttribute(SESSION_FORM_ID, sWord);	// 検索ワードをsessionスコープに保持
		 //ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		 //sResultsをキーとしてvalueをList型にしたものを返す
		model.addAttribute(SEARCH_LIST, r2Service.search(sWord, page, recordPerPage));
		return TO_SEARCH_RESULTS;	// searchResultsに返す
	}

	/**
	 * 文字を検索し、そのページをページングしたとき
	 * @param page	今いるページ
	 * @param model
	 * @return r2Serviceのsearchで処理したSEARCH_LIST、r2Serviceのr2Pagingで処理したページング機能をsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_SEARCH_BUTTON, method = RequestMethod.GET)	// 検索した結果のページでページングを行うとき
	public String getsearchResults(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 保持した検索ワードを取ってくる
		 //ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		 //sResultsをキーとしてvalueをList型にしたものを返す
		model.addAttribute(SEARCH_LIST, r2Service.search(sWord, page, recordPerPage));
		return TO_SEARCH_RESULTS;	// searchResultsに返す
	}
}
