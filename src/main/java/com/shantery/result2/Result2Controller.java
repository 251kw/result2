package com.shantery.result2;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Result2Controller {
//	@Autowired
//	Result2Repository r2Repository;

	@Autowired
	private Result2Service r2Service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getResult2List(Model model, Pageable pageable) {
		List<Result2> result2 = r2Service.gselectAll(pageable);
		model.addAttribute("page", result2);
		model.addAttribute("words", result2.getContent());
		model.addAttribute("url", "/");
		return "index";
	}
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String postdisplay(@RequestParam(name = "hidden") String honbun, Model model){
		model.addAttribute("text", honbun);
		return "display";
	}

}
