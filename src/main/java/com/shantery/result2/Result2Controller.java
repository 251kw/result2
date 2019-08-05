package com.shantery.result2;

import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shantery.result2.paging.PagingUtil;

@Controller
class Result2Controller {

	private static final int RECORD_PER_PAGE = 10;	// 1ページあたりの表示件数
	private static final int LENGTH = 5;	// << < (1 2 3 4 5)←これの表示数 > >>
	private static final String SESSION_FORM_ID="searchForm";	// キー

	@Autowired
	private Result2Service r2Service;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)	// アプリケーションを起動させたとき、もしくは会社のロゴが押されたとき
	public String index(@RequestParam(required = false) final String page, Model model) throws ParseException {
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// もしsessionスコープ内にデータがあるなら削除する
			session.removeAttribute(SESSION_FORM_ID);
		}
		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute("test", r2Service.find(currentPage, RECORD_PER_PAGE));	// ServiceでSQL文の実行している
		int totalRecordNum = r2Service.count();	// データの総件数を取る
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "index";
	}
	@RequestMapping(value = "/result", method = RequestMethod.POST)	// 本文詳細ボタンが押されたとき
	public String postdisplay(@RequestParam(name = "hidden") String honbun, @RequestParam(name = "page") String page, Model model){
		model.addAttribute("text", honbun);	// メールの本文をキーをtextでセットする
		model.addAttribute("page", page);	// 現在のページ番号を覚えておくためにpageをセットする
		return "display";
	}

	@RequestMapping(value = "/return", method = RequestMethod.POST)	// displayから戻るとき
	public String backIndex(@RequestParam(name = "fromdisplay") final String page, Model model) throws ParseException {
		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum;	// データの総件数を取るための変数
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
			String sWord = "'%" + word + "%'";	// LIKE検索用に%を付ける
			totalRecordNum = r2Service.count2(sWord);	// 検索してヒットしたデータの総件数を取る
			/* sResultsをキーとしてvalueをList型にしたものを返す */
			model.addAttribute("sResults", r2Service.search(sWord, currentPage, RECORD_PER_PAGE));
		} else {	// 検索を行っていないのなら
			totalRecordNum = r2Service.count();	// データの総件数を取る
			/* testをキーとしてvalueをList型にしたものを返す */
		model.addAttribute("test", r2Service.find(currentPage, RECORD_PER_PAGE));
		}
		/* ページングの機能としてキーをpageとしたものをセットする */
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
		return "index";
		} else {	// そうでなければsearchResultsに戻す
			return "searchResults";
	}
	}

	@RequestMapping(value = "/return", method = RequestMethod.GET)	// displayから戻ってページングを行うとき
	public String backIndex2(@RequestParam(required = false) final String page, Model model) throws ParseException {
		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum;	// データの総件数を取るための変数
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
			String sWord = "'%" + word + "%'";	// LIKE検索用に%を付ける
			totalRecordNum = r2Service.count2(sWord);	// 検索してヒットしたデータの総件数を取る
			/* sResultsをキーとしてvalueをList型にしたものを返す */
			model.addAttribute("sResults", r2Service.search(sWord, currentPage, RECORD_PER_PAGE));
		} else {	// 検索を行っていないのなら
			totalRecordNum = r2Service.count();	// データの総件数を取る
			/* testをキーとしてvalueをList型にしたものを返す */
			model.addAttribute("test", r2Service.find(currentPage, RECORD_PER_PAGE));
		}
		/* ページングの機能としてキーをpageとしたものをセットする */
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
			return "index";
		} else {	// そうでなければsearchResultsに戻す
			return "searchResults";
		}
	}

	@RequestMapping(value = "/sresults", method = RequestMethod.POST)	// フリーワードの検索ボタンが押されたとき
	public String postsearchResults(@RequestParam(name = "sWord",required = false) String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		if(sWord == null) {	// もしsWordが取れなければ空文字を入れる
			sWord = "";
		}
		session.setAttribute(SESSION_FORM_ID, sWord);	// 検索ワードをsessionスコープに保持
		String sWord1 = "'%"+ sWord + "%'";	// LIKE検索用に%を付ける
		int currentPage = 1;	// 現在いるページ番号の初期化
		if(page != null) {	// もしpageが存在するなら
			try {
				currentPage = Integer.parseInt(page);	// pageをint型に直して変数に代入
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = r2Service.count2(sWord1);	// 検索して出てきたデータの総件数を返すメソッド
		/*model.addAttribute("sword", sWord);*/
		/* sResultsをキーとしてvalueをList型にしたものを返す */
		model.addAttribute("sResults", r2Service.search(sWord1, currentPage, RECORD_PER_PAGE));
		/* ページングの機能してキーをpageとしたものをセットする  */
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";	// searchREsultsに返す
	}
	@RequestMapping(value = "/sresults", method = RequestMethod.GET)	// 検索した結果のページでページングを行うとき
	public String postsearchResults2(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 保持した検索ワードを取ってくる
		if(sWord == null) {	// もしsWordが取れなければ空文字をセットする
			sWord = "";
		}
		/*model.addAttribute("sword", sWord);*/
		String sWord1 = "'%"+ sWord + "%'";	// LIKE検索用に%を付ける
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count2(sWord1);	// 検索して出てきたデータの総件数を返すメソッド
		/*model.addAttribute("sword", sWord);*/
		/* sResultsをキーとしてvalueをList型にしたものを返す */
		model.addAttribute("sResults", r2Service.search(sWord1, currentPage, RECORD_PER_PAGE));
		/* ページングの機能してキーをpageとしたものをセットする  */
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";	// searchREsultsに返す
	}

	@RequestMapping(value = "/resultascdate", method = RequestMethod.POST)
	public String searchResultsascdate(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord1 = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord1));	//sResultsをキーとしてvalueをList型にしたものを返す
		int currentPage = 1;	//現在いるページ番号初期化
		if(page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = r2Service.count();	// データの総件数を取るための変数
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}


	@RequestMapping(value = "/resultdescdate", method = RequestMethod.POST)
	public String searchResultsdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord2 = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		model.addAttribute("sResults", r2Service.findAllOrderByDateDESC(sWord2));	//sResultsをキーとしてvalueをList型にしたものを返す
		int currentPage = 1;	//現在いるページ番号初期化
		if(page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = r2Service.count();	// データの総件数を取るための変数
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}


	@RequestMapping(value = "/resultasccost", method = RequestMethod.POST)
	public String searchResultsasccost(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord3 = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		model.addAttribute("sResults", r2Service.findAllOrderByCostASC(sWord3));	//sResultsをキーとしてvalueをList型にしたものを返す
		int currentPage = 1;	//現在いるページ番号初期化
		if(page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = r2Service.count();	// データの総件数を取るための変数
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}


	@RequestMapping(value = "/resultdesccost", method = RequestMethod.POST)
	public String searchResultsdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord4 = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		model.addAttribute("sResults", r2Service.findAllOrderByCostDESC(sWord4));	//sResultsをキーとしてvalueをList型にしたものを返す
		int currentPage = 1;	//現在いるページ番号初期化
		if(page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = r2Service.count();	// データの総件数を取るための変数
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}



	@RequestMapping(value = "/resultascage", method = RequestMethod.POST)
	public String searchResultsascage(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord5 = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		model.addAttribute("sResults", r2Service.findAllOrderByAgeASC(sWord5));	//sResultsをキーとしてvalueをList型にしたものを返す
		int currentPage = 1;	//現在いるページ番号初期化
		if(page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();	// データの総件数を取るための変数
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}


	@RequestMapping(value = "/resultdescage", method = RequestMethod.POST)
	public String searchResultsdescage(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord6 = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		model.addAttribute("sResults", r2Service.findAllOrderByAgeDESC(sWord6));	//sResultsをキーとしてvalueをList型にしたものを返す
		int currentPage = 1;	//現在いるページ番号初期化
		if(page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();	// データの総件数を取るための変数
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}

}
