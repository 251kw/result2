package com.shantery.result2.sort;

import static com.shantery.result2.util.Result2Constants.*;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.domain.Sort;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class SortController {
	@Autowired
	private SortService sortService;

	@Autowired
	SortRepository sortRep;

	@Autowired
	HttpSession session;

	@RequestMapping(value = FROM_DATEASC_BUTTON, method = RequestMethod.POST)
	public String postascdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByDateASC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByDateASC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_DATEASC_BUTTON, method = RequestMethod.GET)
	public String getascdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByDateASC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByDateASC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_DATEDESC_BUTTON, method = RequestMethod.POST)
	public String postdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);
		model.addAttribute(PAGING,sortService.Paging(word, page));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByDateDESC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByDateDESC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_DATEDESC_BUTTON, method = RequestMethod.GET)
	public String getdescdate(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);
		model.addAttribute(PAGING,sortService.Paging(word, page));
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByDateDESC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByDateDESC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_COSTASC_BUTTON, method = RequestMethod.POST)
	public String postasccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByCostASC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByCostASC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_COSTASC_BUTTON, method = RequestMethod.GET)
	public String getasccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByCostASC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByCostASC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_COSTDESC_BUTTON, method = RequestMethod.POST)
	public String postdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByCostDESC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByCostDESC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_COSTDESC_BUTTON, method = RequestMethod.GET)
	public String getdesccost(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByCostDESC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByCostDESC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_AGEASC_BUTTON, method = RequestMethod.POST)
	public String postascage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByAgeASC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByAgeASC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_AGEASC_BUTTON, method = RequestMethod.GET)
	public String getascage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByAgeASC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByAgeASC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_AGEDESC_BUTTON, method = RequestMethod.POST)
	public String postdescage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByAgeDESC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByAgeDESC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}

	@RequestMapping(value = FROM_AGEDESC_BUTTON, method = RequestMethod.GET)
	public String getdescage(@RequestParam(required = false) final String page, Model model) throws ParseException {
		String word = (String) session.getAttribute(SESSION_FORM_ID);	// 検索ワードを取る
		model.addAttribute(PAGING, sortService.Paging(word, page));	// 検索ワードを使ってページングの設定
		if (word == null) {	// もしwordがnullなら検索を行っていないのでindexに戻る。
			model.addAttribute(LIST, sortService.findAllOrderByAgeDESC(page, word)); //LISTをキーとしてvalueをList型にしたものを返す
			return TO_TOP;
		} else {	// nullじゃなければ検索を行っているのでsearchResultsへ戻る
			model.addAttribute(SEARCH_LIST, sortService.findAllOrderByAgeDESC(page, word)); //SEARCH_LISTをキーとしてvalueをList型にしたものを返す
			return TO_SEARCH_RESULTS;
		}
	}
}
