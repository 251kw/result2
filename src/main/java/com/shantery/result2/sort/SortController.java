package com.shantery.result2.sort;

import java.util.HashMap;
//import java.util.List;

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
	SortRepository sR;

	@RequestMapping(value = "/ascdate", method = RequestMethod.POST)
	public String index(@RequestParam(required = false) final String page, Model model) {

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

		/*
		 * List<Result2> ascDate = sR.findAllOrderByDateASC( new
		 * Sort(Sort.Direction.ASC,"date") );
		 */
		model.addAttribute("test", /*ascDate*/sR.findAllOrderByDateASC());
		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/descdate", method = RequestMethod.POST)
	/*
	 * public String index2(Model model) { List<Result2> descDate = sR.findAll(new
	 * Sort(Sort.Direction.DESC,"date")); model.addAttribute("test",descDate);
	 * model.addAttribute("honbun", new Result2());
	 */
	public String index2(@RequestParam(required = false) final String page, Model model) {

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

		
		model.addAttribute("test", sR.findAllOrderByDateDESC());
		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/asccost", method = RequestMethod.POST)
	public String index3(@RequestParam(required = false) final String page, Model model) {

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

		
		model.addAttribute("test", sR.findAllOrderByCostASC());
		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/desccost", method = RequestMethod.POST)
	public String index4(@RequestParam(required = false) final String page, Model model) {

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

		
		model.addAttribute("test", sR.findAllOrderByCostDESC());
		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/ascage", method = RequestMethod.POST)
	public String index5(@RequestParam(required = false) final String page, Model model) {

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

		
		model.addAttribute("test", sR.findAllOrderByAgeASC());
		model.addAttribute("honbun", new Result2());
		return "index";
	}

	@RequestMapping(value = "/descage", method = RequestMethod.POST)
	public String index6(@RequestParam(required = false) final String page, Model model) {

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

		
		model.addAttribute("test", sR.findAllOrderByAgeDESC());
		model.addAttribute("honbun", new Result2());
		return "index";
	}
}
