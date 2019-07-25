package com.shantery.result2.sort;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	public String index(@RequestParam(required = false) final String page,Model model) {
		
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
		
		List<Result2> ascDate = 
				sR.findAllOrderByDateASC(/* new Sort(Sort.Direction.ASC,"date") */);
			model.addAttribute("test",ascDate);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "/descdate", method = RequestMethod.POST)
	/*
	 * public String index2(Model model) { List<Result2> descDate = sR.findAll(new
	 * Sort(Sort.Direction.DESC,"date")); model.addAttribute("test",descDate);
	 * model.addAttribute("honbun", new Result2());
	 */
public String index2(@RequestParam(required = false) final String page,Model model) {
		
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
		
		List<Result2>descDate = 
				sR.findAllOrderByDateDESC(/* new Sort(Sort.Direction.ASC,"date") */);
			model.addAttribute("test",descDate);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "/asccost", method = RequestMethod.POST)
	public String index3(Model model) {
		List<Result2> ASCcost = 
				sR.findAll(new Sort(Sort.Direction.ASC,"cost"));
			model.addAttribute("test",ASCcost);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "/desccost", method = RequestMethod.POST)
	public String index4(Model model) {
			List<Result2> DESCcost = 
					sR.findAll(new Sort(Sort.Direction.DESC,"cost"));
			model.addAttribute("test",DESCcost);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "/ascage", method = RequestMethod.POST)
	public String index5(Model model) {
			List<Result2> ascAge = 
			    sR.findAll(new Sort(Sort.Direction.ASC,"age"));
			model.addAttribute("test",ascAge);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "/descage", method = RequestMethod.POST)
	public String index6(Model model) {
			List<Result2> descAge = 
			    sR.findAll(new Sort(Sort.Direction.DESC,"age"));
			model.addAttribute("test",descAge);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
}
