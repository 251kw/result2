package com.shantery.result2.sort;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shantery.result2.Result2;

/**
 * @author k.teruya
 * sqlを実行し、データ検索を行う
 */
@Repository
public interface SortRepository extends JpaRepository<Result2, Long> {
	//検索ワードとページング機能を受け取り、日付昇順したLISTを返す。
	public List<Result2> findAllOrderByDateASC(@Param("word") String word, Pageable pageable);

	//検索ワードとページング機能を受け取り、日付降順したLISTを返す。
	public List<Result2> findAllOrderByDateDESC(@Param("word") String word, Pageable pageable);

	//検索ワードとページング機能を受け取り、単金昇順したLISTを返す。
	public List<Result2> findAllOrderByCostASC(@Param("word") String word, Pageable pageable);

	//検索ワードとページング機能を受け取り、単金降順したLISTを返す。
	public List<Result2> findAllOrderByCostDESC(@Param("word") String word, Pageable pageable);

	//検索ワードとページング機能を受け取り、年齢昇順したLISTを返す。
	public List<Result2> findAllOrderByAgeASC(@Param("word") String word, Pageable pageable);

	//検索ワードとページング機能を受け取り、年齢降順したLISTを返す。
	public List<Result2> findAllOrderByAgeDESC(@Param("word") String word, Pageable pageable);

	//ページングの制御機能を受け取り、日付昇順したLISTを返す。
	public List<Result2> findAllOrderByDate(PageRequest of);

	//検索ワードを受け取り、検索結果の総数のLISTを返す。
	public int countAll(@Param("sWord") String sWord);

}
