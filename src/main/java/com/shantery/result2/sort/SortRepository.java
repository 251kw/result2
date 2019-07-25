package com.shantery.result2.sort;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shantery.result2.Result2;

@Repository
public interface SortRepository extends JpaRepository<Result2, Long> {
	public List<Result2> findAllOrderByDateASC();

	public List<Result2> findAllOrderByDateDESC();
}
