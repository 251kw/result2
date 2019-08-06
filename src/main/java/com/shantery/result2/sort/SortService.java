package com.shantery.result2.sort;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shantery.result2.Result2;
import com.shantery.result2.paging.PagingUtil;
import com.shantery.result2.paging.PagingView;
import com.shantery.result2.util.Result2Util;

@Service
class SortService {
	@Autowired
	SortRepository sRepository;

	@Value("${app.recordperpage}")
	private int recordPerPage; // 1ページあたりの表示件数
	@Value("${app.paginglength}") // << < (1 2 3 4 5)←これの表示数 > >>
	private int pagingLength;

	public List<Result2> find(int page, int recordPerPage) throws ParseException {
		int offset = page - 1; // 開始位置
		return sRepository.findAllOrderByDate(PageRequest.of(offset, recordPerPage));

	}

	public List<Result2> findAllOrderByDateASC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - 1);
		return sRepository.findAllOrderByDateASC(word, PageRequest.of(offset, recordPerPage));
	}

	public List<Result2> findAllOrderByDateDESC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - 1);
		return sRepository.findAllOrderByDateDESC(word, PageRequest.of(offset, recordPerPage));
	}

	public List<Result2> findAllOrderByCostASC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - 1);
		return sRepository.findAllOrderByCostASC(word, PageRequest.of(offset, recordPerPage));
	}

	public List<Result2> findAllOrderByCostDESC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - 1);
		return sRepository.findAllOrderByCostDESC(word, PageRequest.of(offset, recordPerPage));
	}

	public List<Result2> findAllOrderByAgeASC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - 1);
		return sRepository.findAllOrderByAgeASC(word, PageRequest.of(offset, recordPerPage));
	}

	public List<Result2> findAllOrderByAgeDESC(String page, String sWord) throws ParseException {
		int currentPage = Result2Util.getCurrentPage(page);
		String word = Result2Util.getSearchWord(sWord);
		int offset = (currentPage - 1);
		return sRepository.findAllOrderByAgeDESC(word, PageRequest.of(offset, recordPerPage));
	}
	public PagingView Paging(String sWord,String page) {
		int currentPage = Result2Util.getCurrentPage(page);
		int totalRecordNum = count(Result2Util.getSearchWord(sWord));
		return PagingUtil.generatePagingView(
				currentPage,
				totalRecordNum,
				recordPerPage,
				pagingLength,
				new HashMap<>());
	}
	int count(String sWord) {	// データの総件数を返すメソッド
		return sRepository.countAll(sWord);
	}


}
