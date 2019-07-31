package com.shantery.result2;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shantery.result2.util.PagingUtil;

@Controller
public class Result2Controller {
//	@Autowired
//	Result2Repository r2Repository;

	private static final int RECORD_PER_PAGE = 10;	// 1ページあたりの表示件数
	private static final int LENGTH = 5;	// << < (1 2 3 4 5)←これの表示数 > >>

	@Autowired
	private Result2Service r2Service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@RequestParam(required = false) final String page, Model model) throws ParseException {
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		model.addAttribute("test", r2Service.find(currentPage, RECORD_PER_PAGE));	// ServiceでSQL文の実行している
		int totalRecordNum = r2Service.count();
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
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String postdisplay(@RequestParam(name = "hidden") String honbun, @RequestParam(name = "page") String page, Model model){
		model.addAttribute("text", honbun);
		model.addAttribute("page", page);
		return "display";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)	// displayから戻るとき
	public String backIndex(@RequestParam(name = "fromdisplay") final String page, Model model) throws ParseException {
		int currentPage = 1;
		if(page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
		}
		model.addAttribute("test", r2Service.find(currentPage, RECORD_PER_PAGE));
		int totalRecordNum = r2Service.count();
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


	@RequestMapping(value = "/sresults", method = RequestMethod.POST)
	public String postsearchResults(@RequestParam(name = "sWord") String sWord, @RequestParam(required = false) final String page, Model model) throws ParseException{
		String sWord1 = "'%"+ sWord + "%'";
		model.addAttribute("sResults", r2Service.search(sWord1));
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

	@RequestMapping(value = "/resultascdate", method = RequestMethod.POST)
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
	}

}
