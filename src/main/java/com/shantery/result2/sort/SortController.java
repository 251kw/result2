package com.shantery.result2.sort;

import java.text.ParseException;
import java.util.HashMap;
//import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.domain.Sort;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shantery.result2.Result2;
import com.shantery.result2.Result2Service;
import com.shantery.result2.util.PagingUtil;

@Controller
public class SortController {

	private static final int RECORD_PER_PAGE = 10;
	private static final int LENGTH = 5;


	@Autowired
	private Result2Service r2Service;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/ascdate", method = RequestMethod.POST)
	public String index(@RequestParam(name = "ascdate")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {
			sWord =("");
		}

		String sWord2 = "'%"+ sWord + "%'";
		int currentPage = 1;
		if (page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, RECORD_PER_PAGE, LENGTH, new HashMap<>()));

		model.addAttribute("test",r2Service.findAllOrderByDateASC(sWord2));
		model.addAttribute("honbun", new Result2());
		return "index";
	}

		@RequestMapping(value = "/descdate", method = RequestMethod.POST)
		public String index2(@RequestParam(name = "descdate")String sWord,@RequestParam(required = false) final String page, Model model)throws ParseException {
			if (sWord == null) {
				sWord =("");
			}
			String sWord3 = "'%"+ sWord + "%'";
			int currentPage = 1;
			if (page != null) {
				try {
					currentPage = Integer.parseInt(page);
				} catch (NumberFormatException e) {
					currentPage = 1;
				}
			}
			int totalRecordNum = r2Service.count();
			model.addAttribute("paging",
					PagingUtil.generatePagingView(currentPage, totalRecordNum, RECORD_PER_PAGE, LENGTH, new HashMap<>()));


			model.addAttribute("test", r2Service.findAllOrderByDateDESC(sWord3));
			model.addAttribute("honbun", new Result2());
			return "index";
		}


	@RequestMapping(value = "/asccost", method = RequestMethod.POST)
	public String index3(@RequestParam(name = "asccost")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {
			sWord =("");
		}
		String sWord4 = "'%"+ sWord + "%'";
		int currentPage = 1;
		if (page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, RECORD_PER_PAGE, LENGTH, new HashMap<>()));


		model.addAttribute("test", r2Service.findAllOrderByCostASC(sWord4));
		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/desccost", method = RequestMethod.POST)
	public String index4(@RequestParam(name = "desccost")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {
			sWord =("");
		}
		String sWord5 = "'%"+ sWord + "%'";
		int currentPage = 1;
		if (page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, RECORD_PER_PAGE, LENGTH, new HashMap<>()));


		model.addAttribute("test", r2Service.findAllOrderByCostDESC(sWord5));
		model.addAttribute("honbun", new Result2());
		return "index";
	}


	@RequestMapping(value = "/ascage", method = RequestMethod.POST)
	public String index5(@RequestParam(name = "ascage")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {
			sWord =("");
		}
		String sWord6 = "'%"+ sWord + "%'";
		int currentPage = 1;
		if (page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, RECORD_PER_PAGE, LENGTH, new HashMap<>()));


		model.addAttribute("test", r2Service.findAllOrderByAgeASC(sWord6));
		model.addAttribute("honbun", new Result2());
		return "index";
	}


	@RequestMapping(value = "/descage", method = RequestMethod.POST)
	public String index6(@RequestParam(name = "descage")String sWord,@RequestParam(required = false) final String page, Model model) throws ParseException {
		if (sWord == null) {
			sWord =("");
		}
		String sWord7 = "'%"+ sWord + "%'";
		int currentPage = 1;
		if (page != null) {
			try {
				currentPage = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
		}
		int totalRecordNum = r2Service.count();
		model.addAttribute("paging",
				PagingUtil.generatePagingView(currentPage, totalRecordNum, RECORD_PER_PAGE, LENGTH, new HashMap<>()));


		model.addAttribute("test", r2Service.findAllOrderByAgeDESC(sWord7));
		model.addAttribute("honbun", new Result2());
		return "index";
	}
}
