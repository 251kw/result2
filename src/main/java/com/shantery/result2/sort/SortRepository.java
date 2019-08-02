package com.shantery.result2.sort;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shantery.result2.Result2;

@Repository
public interface SortRepository extends JpaRepository<Result2, Long> {
	public List<Result2> findAllSortOrderByDateAsc(@Param("word") String word);

	public List<Result2> findAllOrderByAgeASC(String sWord);

	public List<Result2> findAllOrderByDateDESC(String sWord);

	public List<Result2> findAllOrderByCostASC(String sWord);

	public List<Result2> findAllOrderByCostDESC(String sWord);

	public List<Result2> findAllOrderByAgeDESC(String sWord);

	public List<Result2> findAllOrderByDate(PageRequest of);






}