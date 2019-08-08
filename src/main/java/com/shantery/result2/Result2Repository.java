package com.shantery.result2;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author k.teruya
 * sqlを実行し、データ検索を行う
 */
@Repository
public interface Result2Repository extends JpaRepository<Result2, Long> {
	public List<Result2> findAllOrderByDate(Pageable pageable);
	//ページングの機能を受け取り、データを日付昇順に並べたLISTを返す。
	public int countAll(@Param("sWord") String sWord);
	//検索ワードを受け取り、検索結果データの総数のLISTを返す。
	public List<Result2> search(@Param("sWord") String sWord, Pageable pageable);
	//検索ワードとページングの機能を受け取り、検索結果のLISTを返す。
}