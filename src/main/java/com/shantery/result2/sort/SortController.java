package com.shantery.result2.sort;

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
class SortController {
	@Autowired
	private SortService sortService;

	@Autowired
	HttpSession session;

	@Value("${app.columns}")
    private String columns;	// 1ページあたりの表示件数

	@Value("${app.recordperpage}")
	private int recordPerPage;	// 1ページあたりの表示件数

	/**
	 * 日付を昇順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEASC_BUTTON, method = RequestMethod.POST)
	public String postascdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}


	/**
	 * 日付を降順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEASC_BUTTON, method = RequestMethod.GET)
	public String getascdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 日付を降順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEDESC_BUTTON, method = RequestMethod.POST)
	public String postdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);
		model.addAttribute(PAGING,sortService.Paging(word, page));
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 日付を降順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEDESC_BUTTON, method = RequestMethod.GET)
	public String getdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);
		model.addAttribute(PAGING,sortService.Paging(word, page));
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 単金を昇順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTASC_BUTTON, method = RequestMethod.POST)
	public String postasccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 *単金を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTASC_BUTTON, method = RequestMethod.GET)
	public String getasccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 単金を降順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTDESC_BUTTON, method = RequestMethod.POST)
	public String postdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 単金を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTDESC_BUTTON, method = RequestMethod.GET)
	public String getdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 年齢を昇順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEASC_BUTTON, method = RequestMethod.POST)
	public String postascage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 年齢を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEASC_BUTTON, method = RequestMethod.GET)
	public String getascage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 年齢を降順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEDESC_BUTTON, method = RequestMethod.POST)
	public String postdescage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	/**
	 * 年齢を降順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model	データを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEDESC_BUTTON, method = RequestMethod.GET)
	public String getdescage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	//検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	//検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns));
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns));
		if (word == null) {	//もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, word))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	//nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, word))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}
}
