package com.shantery.result2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Result2Controller {
	@Autowired
	Result2Repository r2Repository;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Result2> result2 = r2Repository.findAllOrderByName();
		model.addAttribute("test", result2);
		model.addAttribute("honbun", new Result2());
		return "index";
	}
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String postdisplay(@RequestParam(name = "hidden") String honbun, Model model){
		model.addAttribute("text", honbun);
		return "display";
	}

}
