package com.shantery.result2.sort;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shantery.result2.Result2;

@Repository
public interface SortRepository extends JpaRepository<Result2, Long> {
	public List<Result2> findAllSortOrderByDateASC(@Param("word") String word);
	public List<Result2> findAllOrderByDateDESC(@Param("word") String word);
	public List<Result2> findAllOrderByCostASC(@Param("word") String word);
	public List<Result2> findAllOrderByCostDESC(@Param("word") String word);
	public List<Result2> findAllOrderByAgeASC(@Param("word") String word);
	public List<Result2> findAllOrderByAgeDESC(@Param("word") String word);
	public List<Result2> findAllOrderByDate(PageRequest of);
	public Object findAllSortOrderByDateAsc(String sWord);
}