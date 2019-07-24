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
		/*List<Result2> result2 = r2Service.gselectAll(pageable);
		model.addAttribute("page", result2);
		model.addAttribute("words", result2.getContent());
		model.addAttribute("url", "/");*/
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
	public String postdisplay(@RequestParam(name = "hidden") String honbun, Model model){
		model.addAttribute("text", honbun);
		return "display";
	}

}
