package com.shantery.result2;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Result2Repository extends JpaRepository<Result2, Long> {
	public List<Result2> rfindAllOrderByDate(Pageable pageable);

	public List<Result2> rfindAllOrderByDateASC(String sWord);

	public List<Result2> rfindAllOrderByDateDESC(String sWord);

	public List<Result2> rfindAllOrderByCostASC(String sWord);

	public List<Result2> rfindAllOrderByCostDESC(String sWord);

	public List<Result2> rfindAllOrderByAgeASC(String sWord);

	public List<Result2> rfindAllOrderByAgeDESC(String sWord);
}