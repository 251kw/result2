package com.shantery.result2;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Result2Controller {

	private static final int RECORD_PER_PAGE = 10;	// 1ページあたりの表示件数
	//private static final int LENGTH = 5;	// << < (1 2 3 4 5)←これの表示数 > >>
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
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute("test", r2Service.find(page, RECORD_PER_PAGE));	// ServiceでSQL文の実行している
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		String sWord = "";
		model.addAttribute(
				"paging",r2Service.r2Paging(sWord,page));
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
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			 //sResultsをキーとしてvalueをList型にしたものを返す
			model.addAttribute("sResults", r2Service.search(sWord, page, RECORD_PER_PAGE));
		} else {	// 検索を行っていないのなら
			 //testをキーとしてvalueをList型にしたものを返す
		model.addAttribute("test", r2Service.find(page, RECORD_PER_PAGE));
		}
		 //ページングの機能としてキーをpageとしたものをセットする
		model.addAttribute(
				"paging",
				r2Service.r2Paging(sWord,page));
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
		return "index";
		} else {	// そうでなければsearchResultsに戻す
			return "searchResults";
		}
	}

	@RequestMapping(value = "/return", method = RequestMethod.GET)	// displayから戻ってページングを行うとき
	public String backIndex2(@RequestParam(required = false) final String page, Model model) throws ParseException {
		if(page != null) {	// pageが存在するとき
			String sWord = "";
			model.addAttribute(
					"paging",
					r2Service.r2Paging(sWord,page));
		}
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			 //sResultsをキーとしてvalueをList型にしたものを返す
			model.addAttribute("sResults", r2Service.search(sWord,page, RECORD_PER_PAGE));
		} else {	// 検索を行っていないのなら
			 //testをキーとしてvalueをList型にしたものを返す
			model.addAttribute("test", r2Service.find(page, RECORD_PER_PAGE));
		}
		 //ページングの機能としてキーをpageとしたものをセットする
		model.addAttribute(
				"paging",
				r2Service.r2Paging(sWord,page));
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
		if(page != null) {	// もしpageが存在するなら
			model.addAttribute(
					"paging",
					r2Service.r2Paging(sWord,page));
		}
		model.addAttribute("sword", sWord);
		 //sResultsをキーとしてvalueをList型にしたものを返す
		model.addAttribute("sResults", r2Service.search(sWord, page, RECORD_PER_PAGE));
		 //ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				"paging",
				r2Service.r2Paging(sWord,page));
		return "searchResults";	// searchResultsに返す
	}
	@RequestMapping(value = "/sresults", method = RequestMethod.GET)	// 検索した結果のページでページングを行うとき
	public String postsearchResults2(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 保持した検索ワードを取ってくる
		if(sWord == null) {	// もしsWordが取れなければ空文字をセットする
			sWord = "";
		}
		model.addAttribute("sword", sWord);
		 //sResultsをキーとしてvalueをList型にしたものを返す
		model.addAttribute("sResults", r2Service.search(sWord, page, RECORD_PER_PAGE));
		 //ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				"paging",
				r2Service.r2Paging(sWord,page));
		return "searchResults";	// searchResultsに返す
	}

/*	@RequestMapping(value = "/resultascdate", method = RequestMethod.POST)
	public String searchResults1(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord2 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord2));
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
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
	public String searchResults2(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord3 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord3));
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
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
	public String searchResults3(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord4 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord4));
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
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
	public String searchResults4(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord5 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord5));
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
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
	public String searchResults5(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord6 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord6));
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
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
	public String searchResults6(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord7 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.findAllOrderByDateASC(sWord7));
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
		model.addAttribute(
				"paging",
				PagingUtil.generatePagingView(
						currentPage,
						totalRecordNum,
						RECORD_PER_PAGE,
						LENGTH,
						new HashMap<>()));
		return "searchResults";
	}*/

}
