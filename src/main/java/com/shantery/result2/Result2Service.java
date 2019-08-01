package com.shantery.result2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Result2Service {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	Result2Repository r2Repository;

	public List<Result2> find(int page, int recordPerPage) throws ParseException{
		int offset = (page-1)/** recordPerPage*/;	// 開始位置
		return r2Repository.findAllOrderByDate(PageRequest.of(offset, recordPerPage));


		/** ↓不要であれば消してください↓ **/
		/*Map<String, Integer> conds = new HashMap<String, Integer>() {{
			put("offset", offset);
			put("record", recordPerPage);
		}};
		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp LIMIT :record OFFSET :offset",
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
		);*/
	}

	public int count() {	// データの総件数を返すメソッド
		return namedParameterJdbcTemplate.query(
				"SELECT COUNT(*) FROM result2disp",
				(rs, i) -> rs.getInt(1)
		).get(0);
	}

	public List<Result2> search(String sWord, int page, int recordPerPage) throws ParseException{
		int offset = (page-1)* recordPerPage;
		Map<String, Integer> conds = new HashMap<String, Integer>() {{
			put("offset", offset);
			put("record", recordPerPage);
		}};

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE " + sWord + "OR SENDER LIKE " + sWord + " OR TEXT LIKE " + sWord +  " OR CLOSEST_STATION LIKE " + sWord + " LIMIT :record OFFSET :offset",
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

	public List<Result2> findAllOrderByDateASC(String sWord) throws ParseException{

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE" + sWord + "OR SENDER LIKE" + sWord + "OR TEXT LIKE" + sWord + " OR CLOSEST_STATION LIKE" + sWord
				+" ORDER BY DATE ASC",

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

	public List<Result2> findAllOrderByDateDESC(String sWord) throws ParseException{

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE" + sWord + "OR SENDER LIKE" + sWord + "OR TEXT LIKE" + sWord + " OR CLOSEST_STATION LIKE" + sWord
				+" ORDER BY DATE DESC",

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

	public List<Result2> findAllOrderByCostASC(String sWord) throws ParseException{

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE" + sWord + "OR SENDER LIKE" + sWord + "OR TEXT LIKE" + sWord + " OR CLOSEST_STATION LIKE" + sWord
				+"ORDER BY LPAD(COST,10,0)ASC;",

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

	public List<Result2> findAllOrderByCostDESC(String sWord) throws ParseException{

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE" + sWord + "OR SENDER LIKE" + sWord + "OR TEXT LIKE" + sWord + " OR CLOSEST_STATION LIKE" + sWord
				+"ORDER BY LPAD(COST,10,0)DESC",

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

	public List<Result2> findAllOrderByAgeASC(String sWord) throws ParseException{

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE" + sWord + "OR SENDER LIKE" + sWord + "OR TEXT LIKE" + sWord + " OR CLOSEST_STATION LIKE" + sWord
				+" ORDER BY AGE ASC",

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

	public List<Result2> findAllOrderByAgeDESC(String sWord) throws ParseException{

		return namedParameterJdbcTemplate.query(
				"SELECT * FROM result2disp WHERE EMPLOYMENT LIKE" + sWord + "OR SENDER LIKE" + sWord + "OR TEXT LIKE" + sWord + " OR CLOSEST_STATION LIKE" + sWord
				+" ORDER BY AGE DESC",

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

	public int count2(String sWord) {	// データの総件数を返すメソッド
		return namedParameterJdbcTemplate.query(
				"SELECT COUNT(*) FROM result2disp WHERE EMPLOYMENT LIKE " + sWord + "OR SENDER LIKE " + sWord + " OR TEXT LIKE " + sWord +  " OR CLOSEST_STATION LIKE " + sWord  ,
				(rs, i) -> rs.getInt(1)
		).get(0);
	}
}