package com.shantery.result2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Result2Repository extends JpaRepository<Result2, Long> {
	public List<Result2> findAllOrderByName();
}
