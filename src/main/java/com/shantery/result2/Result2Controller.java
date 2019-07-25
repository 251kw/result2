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

	private static final int RECORD_PER_PAGE = 10;
	private static final int LENGTH = 5;

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

}
