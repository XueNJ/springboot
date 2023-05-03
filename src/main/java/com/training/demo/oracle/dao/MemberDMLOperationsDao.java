package com.training.demo.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.demo.oracle.entity.Bevrage_Member;

@Repository
public interface MemberDMLOperationsDao extends JpaRepository<Bevrage_Member, Long>{	
}
