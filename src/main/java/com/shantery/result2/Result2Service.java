package com.shantery.result2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Result2Service {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Result2> find(int page, int recordPerPage) throws ParseException{
		int offset = (page-1)* recordPerPage + 1;
		int require = page * recordPerPage;
		Map<String, Integer> conds = new HashMap<String, Integer>() {{
			put("offset", offset);
			put("require", require);
		}};
		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE result_id BETWEEN :offset AND :require",
				conds,
				(rs,i) -> {
					try {
						return new Result2(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
								rs.getString(10), rs.getString(11));
					} catch (ParseException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					return null;
				}
		);
	}

	public int count() {
		return namedParameterJdbcTemplate.query(
				"SELECT COUNT(*) FROM result2disp",
				(rs, i) -> rs.getInt(1)
		).get(0);
	}
}