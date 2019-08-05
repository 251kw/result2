/*package com.shantery.result2.sort;

import java.text.ParseException;
import java.util.HashMap;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shantery.result2.paging.PagingUtil;

@Controller
class SortController {

	@Value("${app.recordperpage}")
    private int recordPerPage;
	@Value("${app.paginglength}")
    private int pagingLength;
	@Autowired
	private SortService sortService;

	@Autowired
	SortRepository sortRep;

	@RequestMapping(value = "/ascdate", method = RequestMethod.POST)
	public String indexascdate(@RequestParam(name = "ascdate")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) { //検索文字がない場合、空文字。
			sWord =("");
		}

		int currentPage = 1; //現在いるページ番号初期化
		if (page != null) {	//pageが存在するとき
			try {
				currentPage = Integer.parseInt(page);	// 現在いるページ番号を取る
			} catch (NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = sortService.count();	// データの総件数を取るための変数
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, recordPerPage, pagingLength, new HashMap<>()));

		model.addAttribute("test", sortService.findAllOrderByDateASC(sWord));	//testをキーとしてvalueをList型にしたものを返す
//		model.addAttribute("honbun", new Result2());
		return "index";
	}

		@RequestMapping(value = "/descdate", method = RequestMethod.POST)
		public String indexsortdescdate(@RequestParam(name = "descdate")String sWord,@RequestParam(required = false) final String page, Model model)throws ParseException {
			if (sWord == null) {	// もしsWordが取れなければ空文字を入れる
				sWord =("");
			}

			int currentPage = 1;	// 現在いるページ番号の初期化
			if (page != null) {	// もしpageが存在するなら
				try {
					currentPage = Integer.parseInt(page);	// pageをint型に直して変数に代入
				} catch (NumberFormatException e) {
					currentPage = 1;	// 失敗したら先頭にする
				}
			}
			int totalRecordNum = sortService.count();
			model.addAttribute("paging",
					PagingUtil.generatePagingView(currentPage, totalRecordNum, recordPerPage, pagingLength, new HashMap<>()));


			model.addAttribute("test", sortService.findAllOrderByDateDESC(sWord));	//testをキーとしてvalueをList型にしたものを返す
//			model.addAttribute("honbun", new Result2());
			return "index";
		}




	@RequestMapping(value = "/asccost", method = RequestMethod.POST)
	public String indexsortasccost(@RequestParam(name = "asccost")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {	// もしsWordが取れなければ空文字を入れる
			sWord =("");
		}

		int currentPage = 1;	// 現在いるページ番号の初期化
		if (page != null) {	// もしpageが存在するなら
			try {
				currentPage = Integer.parseInt(page);	// pageをint型に直して変数に代入
			} catch (NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = sortService.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, recordPerPage, pagingLength, new HashMap<>()));


		model.addAttribute("test", sortService.findAllOrderByCostASC(sWord));	//testをキーとしてvalueをList型にしたものを返す
//		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/desccost", method = RequestMethod.POST)
	public String indexsortdesccost(@RequestParam(name = "desccost")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {	// もしsWordが取れなければ空文字を入れる
			sWord =("");
		}

		int currentPage = 1;	// 現在いるページ番号の初期化
		if (page != null) {	// もしpageが存在するなら
			try {
				currentPage = Integer.parseInt(page);	// pageをint型に直して変数に代入
			} catch (NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = sortService.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, recordPerPage, pagingLength, new HashMap<>()));


		model.addAttribute("test", sortService.findAllOrderByCostDESC(sWord));	//testをキーとしてvalueをList型にしたものを返す
//		model.addAttribute("honbun", new Result2());
		return "index";
	}


	@RequestMapping(value = "/ascage", method = RequestMethod.POST)
	public String indexsortascage(@RequestParam(name = "ascage")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {	// もしsWordが取れなければ空文字を入れる
			sWord =("");
		}

		int currentPage = 1;	// 現在いるページ番号の初期化
		if (page != null) {	// もしpageが存在するなら
			try {
				currentPage = Integer.parseInt(page);	// pageをint型に直して変数に代入
			} catch (NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = sortService.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, recordPerPage, pagingLength, new HashMap<>()));


		model.addAttribute("test", sortService.findAllOrderByAgeASC(sWord));	//testをキーとしてvalueをList型にしたものを返す
//		model.addAttribute("honbun", new Result2());
		return "index";
	}


	@RequestMapping(value = "/descage", method = RequestMethod.POST)
	public String indexsortdescage(@RequestParam(name = "descage")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {	// もしsWordが取れなければ空文字を入れる
			sWord =("");
		}

		int currentPage = 1;	// 現在いるページ番号の初期化
		if (page != null) {	// もしpageが存在するなら
			try {
				currentPage = Integer.parseInt(page);	// pageをint型に直して変数に代入
			} catch (NumberFormatException e) {
				currentPage = 1;	// 失敗したら先頭にする
			}
		}
		int totalRecordNum = sortService.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, recordPerPage, pagingLength, new HashMap<>()));


		model.addAttribute("test", sortService.findAllOrderByAgeDESC(sWord));	//testをキーとしてvalueをList型にしたものを返す
//		model.addAttribute("honbun", new Result2());
		return "index";
	}
}
*/