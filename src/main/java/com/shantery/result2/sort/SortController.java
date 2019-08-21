package com.shantery.result2.sort;

import static com.shantery.result2.util.Result2Constants.*;

import java.text.ParseException;
import java.util.Optional;

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
	private SortService sortService; //呼び出すクラス
	@Autowired
	SortRepository sortRep; //呼び出すクラス
	@Autowired
	HttpSession session; //呼び出すクラス
	@Value("${app.columns}")
	private String columns; // 1ページあたりの表示件数
	@Value("${app.recordperpage}")
	private int recordPerPage; // 1ページあたりの表示件数

	/**
	 * 日付を昇順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEASC_BUTTON, method = RequestMethod.POST)
	public String postascdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); // 検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); // 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, sWord, search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, sWord, search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 日付を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEASC_BUTTON, method = RequestMethod.GET)
	public String getascdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); // 検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); // 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, sWord,search_kbn)));
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateASC(page, sWord,search_kbn)));
			return TO_TOP;
		}
	}

	/**
	 * 日付を降順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEDESC_BUTTON, method = RequestMethod.POST)
	public String postdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); // 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 日付を降順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByDateDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByDateDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_DATEDESC_BUTTON, method = RequestMethod.GET)
	public String getdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); // 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByDateDESC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 単金を昇順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTASC_BUTTON, method = RequestMethod.POST)
	public String postasccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); // 検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 *単金を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTASC_BUTTON, method = RequestMethod.GET)
	public String getasccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostASC(page, sWord, search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 単金を降順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTDESC_BUTTON, method = RequestMethod.POST)
	public String postdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 単金を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByCostDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByCostDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_COSTDESC_BUTTON, method = RequestMethod.GET)
	public String getdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByCostDESC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 年齢を昇順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEASC_BUTTON, method = RequestMethod.POST)
	public String postascage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}


	/**
	 * 年齢を昇順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeASCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeASCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEASC_BUTTON, method = RequestMethod.GET)
	public String getascage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeASC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 年齢を降順にソート(POST送信の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEDESC_BUTTON, method = RequestMethod.POST)
	public String postdescage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}

	/**
	 * 年齢を降順にソート(POST送信以外の時)
	 * @param page	今いるページ
	 * @param model 利用するデータを管理するためのクラス
	 * @return	検索を行っていなければ、sortServiceのfindAllOrderByAgeDESCで処理したLISTをindexに返す。
	 * @return	検索を行っていれば、sortServiceのfindAllOrderByAgeDESCで処理したSEARCH_LISTをsearchResultsに返す。
	 */
	@RequestMapping(value = FROM_AGEDESC_BUTTON, method = RequestMethod.GET)
	public String getdescage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String sWord = (String) session.getAttribute(SESSION_FORM_ID); //検索ワードを取る
		String search_kbn = (String) session.getAttribute(SESSION_FORM_KEY); // 人材情報か案件情報かを調べるためのセッション
		model.addAttribute(PAGING, sortService.Paging(sWord, page,search_kbn)); //検索ワードを使ってページングの設定
		model.addAttribute(COLUMN_HEAD, Result2Util.getColumnName(columns)); //表題をキーとして、表示する見出しを返す
		model.addAttribute(COLUMN_LENGTH, Result2Util.getColumnCount(columns)); //表の長さをキーとして、表示する見出しの数を返す
		Optional<String> wordOpt = Optional.ofNullable(sWord); //Optional型に変換する
		if (wordOpt.isPresent()) { //もしwordOptに中身が存在すれば
			//SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, sWord,search_kbn))); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(SEARCH_WORD,sWord);
			return TO_SEARCH_RESULTS;
		} else { //もしwordOptに中身が存在しなければ
			//LISTをキーとしてvalueをList型にしたものを返す
			model.addAttribute(LIST, Result2Util.convBeanToList(sortService.findAllOrderByAgeDESC(page, sWord,search_kbn))); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		}
	}
}
