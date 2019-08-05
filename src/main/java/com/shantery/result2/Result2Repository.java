package com.shantery.result2;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Result2Repository extends JpaRepository<Result2, Long> {
	public List<Result2> rfindAllOrderByDate(Pageable pageable);

	public List<Result2> rfindAllOrderByAgeASC(@Param("word")String sWord);

	public List<Result2> rfindAllOrderByAgeDESC(@Param("word")String sWord);

	public List<Result2> rfindAllOrderByCostASC(@Param("word")String sWord);

	public List<Result2> rfindAllOrderByCostDESC(@Param("word")String sWord);

	public List<Result2> rfindAllOrderByDateDESC(@Param("word")String sWord);

	public List<Result2> rfindAllOrderByDateASC(@Param("word")String sWord);

	public List<Result2> rfindAllOrderByDESC(@Param("word")String sWord);



}