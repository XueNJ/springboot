package com.training.demo.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.demo.oracle.entity.Bevrage_Goods;

@Repository
public interface DMLOperationsBevrageGoodsDao extends JpaRepository<Bevrage_Goods, Long>{	
}
