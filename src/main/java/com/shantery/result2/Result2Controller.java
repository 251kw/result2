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
public class Result2Controller {

	@Value("${app.recordperpage}")
	private int recordPerPage;	// 1ページあたりの表示件数
	//private static final int LENGTH = 5;	// << < (1 2 3 4 5)←これの表示数 > >>

	@Autowired
	private Result2Service r2Service;
	@Autowired
	HttpSession session;

	@RequestMapping(value = TOP, method = RequestMethod.GET)	// アプリケーションを起動させたとき、もしくは会社のロゴが押されたとき
	public String index(@RequestParam(required = false) final String page, Model model) throws ParseException {
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// もしsessionスコープ内にデータがあるなら削除する
			session.removeAttribute(SESSION_FORM_ID);
		}
		/* キーの値をtestにし、valueをSQL文で返したList型のResult2でセットする。*/
		model.addAttribute(LIST, r2Service.find(page, recordPerPage));	// ServiceでSQL文の実行している
		/* ページングの機能としてキーの値をpageにしたものをセットする */
		String sWord = EMPTY;
		model.addAttribute(
				PAGING,r2Service.r2Paging(sWord,page));
		return TO_TOP;
	}
	@RequestMapping(value = FROM_TEXT_DETAILS_BUTTON, method = RequestMethod.POST)	// 本文詳細ボタンが押されたとき
	public String postdisplay(@RequestParam(name = HONBUN) String honbun, @RequestParam(name = KEEP_SET_PAGE) String page, Model model){
		model.addAttribute(TEXT, honbun);	// メールの本文をキーをtextでセットする
		model.addAttribute(KEEP_SET_PAGE, page);	// 現在のページ番号を覚えておくためにpageをセットする
		return TO_TEXT_DETAILS;
	}

	@RequestMapping(value = FROM_BACK_BUTTON, method = RequestMethod.POST)	// displayから戻るとき
	public String backIndex(@RequestParam(name = KEEP_GET_PAGE) final String page, Model model) throws ParseException {
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			 //sResultsをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, r2Service.search(sWord, page, recordPerPage));
		} else {	// 検索を行っていないのなら
			 //testをキーとしてvalueをList型にしたものを返す
		model.addAttribute(LIST, r2Service.find(page, recordPerPage));
		}
		 //ページングの機能としてキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
			return TO_TOP;
		} else {	// そうでなければsearchResultsに戻す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_BACK_BUTTON, method = RequestMethod.GET)	// displayから戻ってページングを行うとき
	public String backIndex2(@RequestParam(required = false) final String page, Model model) throws ParseException {
		boolean flag = false;	// フリーワード検索を行っているどうかのフラグ
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 検索されているワードを取る
		/*if(page != null) {	// pageが存在するとき
		}*/
		if(session.getAttribute(SESSION_FORM_ID) != null) {	// 検索を行っているなら
			flag = true;	// フラグを立てる
			 //sResultsをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, r2Service.search(sWord,page, recordPerPage));
		} else {	// 検索を行っていないのなら
			 //testをキーとしてvalueをList型にしたものを返す
			sWord = EMPTY;
			model.addAttribute(LIST, r2Service.find(page, recordPerPage));
		}
		 //ページングの機能としてキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		if(flag == false) {	// もしflagが立っていない(=検索を行っていない)のであればindexに戻す
			return TO_TOP;
		} else {	// そうでなければsearchResultsに戻す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_SEARCH_BUTTON, method = RequestMethod.POST)	// フリーワードの検索ボタンが押されたとき
	public String postsearchResults(@RequestParam(name = SEARCH_WORD,required = false) String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		if(sWord == null) {	// もしsWordが取れなければ空文字を入れる
			sWord = EMPTY;
		}
		session.setAttribute(SESSION_FORM_ID, sWord);	// 検索ワードをsessionスコープに保持
		if(page != null) {	// もしpageが存在しないなら
			model.addAttribute(
					PAGING,
					r2Service.r2Paging(sWord,page));
		}
		 //sResultsをキーとしてvalueをList型にしたものを返す
		model.addAttribute(SEARCH_LIST, r2Service.search(sWord, page, recordPerPage));
		 //ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		return TO_SEARCH_RESULTS;	// searchResultsに返す
	}
	@RequestMapping(value = FROM_SEARCH_BUTTON, method = RequestMethod.GET)	// 検索した結果のページでページングを行うとき
	public String postsearchResults2(@RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord = (String) session.getAttribute(SESSION_FORM_ID);	// 保持した検索ワードを取ってくる
		if(sWord == null) {	// もしsWordが取れなければ空文字をセットする
			sWord = EMPTY;
		}
		 //sResultsをキーとしてvalueをList型にしたものを返す
		model.addAttribute(SEARCH_LIST, r2Service.search(sWord, page, recordPerPage));
		 //ページングの機能してキーをpageとしたものをセットする
		model.addAttribute(
				PAGING,
				r2Service.r2Paging(sWord,page));
		return TO_SEARCH_RESULTS;	// searchResultsに返す
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
