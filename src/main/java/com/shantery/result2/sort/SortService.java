package com.shantery.result2.sort;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.shantery.result2.Result2;



@Service
class SortService {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	SortRepository sRepository;

	public List<Result2> find(int page, int recordPerPage) throws ParseException{
		int offset = page - 1; // 開始位置
		return sRepository.findAllOrderByDate(PageRequest.of(offset, recordPerPage));

	}

	public int count() {	// データの総件数を返すメソッド
		return namedParameterJdbcTemplate.query(
				"SELECT COUNT(*) FROM result2disp",
				(rs, i) -> rs.getInt(1)
		).get(0);
	}

		public List<Result2> findAllOrderByDateASC(String sWord) throws ParseException{

			return sRepository.findAllOrderByDateASC(sWord);
		}

		public List<Result2> findAllOrderByDateDESC(String sWord) throws ParseException{

			return sRepository.findAllOrderByDateDESC(sWord);
		}

		public List<Result2> findAllOrderByCostASC(String sWord) throws ParseException{

			return sRepository.findAllOrderByCostASC(sWord);
		}

		public List<Result2> findAllOrderByCostDESC(String sWord) throws ParseException{

			return sRepository.findAllOrderByCostDESC(sWord);
		}

		public List<Result2> findAllOrderByAgeASC(String sWord) throws ParseException{

			return sRepository.findAllOrderByAgeASC(sWord);
		}

		public List<Result2> findAllOrderByAgeDESC(String sWord) throws ParseException{

			return sRepository.findAllOrderByAgeDESC(sWord);
		}

		public int count2(String sWord) {	// データの総件数を返すメソッド
			return namedParameterJdbcTemplate.query(
					"SELECT COUNT(*) FROM result2disp WHERE EMPLOYMENT LIKE " + sWord + "OR SENDER LIKE " + sWord + " OR TEXT LIKE " + sWord +  " OR CLOSEST_STATION LIKE " + sWord  ,
					(rs, i) -> rs.getInt(1)
			).get(0);
		}


}
