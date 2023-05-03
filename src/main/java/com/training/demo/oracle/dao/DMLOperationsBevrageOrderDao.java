package com.training.demo.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.demo.oracle.entity.Bevrage_Order;

@Repository
public interface DMLOperationsBevrageOrderDao extends JpaRepository<Bevrage_Order, Long>{	
}
