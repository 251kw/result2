package com.shantery.result2.sort;

/*import java.util.Collections;
import java.util.Comparator;*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shantery.result2.Result2;
import com.shantery.result2.Result2Repository;


@Controller
public class SortController {
	@Autowired
	Result2Repository r2Repository;
	@RequestMapping(value = "./ascdate", method = RequestMethod.POST)
	public String index(Model model) {
		List<Result2> ascDate = 
				r2Repository.findAllOrderByName(/* new Sort(Sort.Direction.ASC,"date") */);
		
		/*
		 * Collections.sort(ascDate, new Comparator<Result2>() {
		 * 
		 * @Override public int compare(Result2 result2First, Result2 result2Second) {
		 * 
		 * return result2First.getDate().compareTo(result2First.getDate());
		 * 
		 * } });
		 */
		
			model.addAttribute("test",ascDate);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "./descdate", method = RequestMethod.POST)
	public String index2(Model model) {
		List<Result2> descDate = 
				r2Repository.findAll(new Sort(Sort.Direction.DESC,"date"));
			model.addAttribute("test",descDate);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "./asccost", method = RequestMethod.POST)
	public String index3(Model model) {
		List<Result2> ASCcost = 
				r2Repository.findAll(new Sort(Sort.Direction.ASC,"cost"));
			model.addAttribute("test",ASCcost);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "./desccost", method = RequestMethod.POST)
	public String index4(Model model) {
			List<Result2> DESCcost = 
					r2Repository.findAll(new Sort(Sort.Direction.DESC,"cost"));
			model.addAttribute("test",DESCcost);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "./ascage", method = RequestMethod.POST)
	public String index5(Model model) {
			List<Result2> ascAge = 
			    r2Repository.findAll(new Sort(Sort.Direction.ASC,"age"));
			model.addAttribute("test",ascAge);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
	@RequestMapping(value = "./descage", method = RequestMethod.POST)
	public String index6(Model model) {
			List<Result2> descAge = 
			    r2Repository.findAll(new Sort(Sort.Direction.DESC,"age"));
			model.addAttribute("test",descAge);
			model.addAttribute("honbun", new Result2());
			return "index";
	}
}
