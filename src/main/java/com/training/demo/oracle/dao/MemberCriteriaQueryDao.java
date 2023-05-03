package com.training.demo.oracle.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.demo.oracle.entity.Bevrage_Member;
import com.training.demo.vo.InputMemberLogin;
import com.training.demo.vo.OutputMember;

@Repository
public class MemberCriteriaQueryDao {

	@PersistenceContext(name = "oracleEntityManager")
	private EntityManager entityManager;
	@Autowired
	private OutputMember outputMember;
	// ----------- JPA Criteria Queries ------------
	/*
	 * (後台)商品列表查詢 :
	 */
	public OutputMember queryMember(InputMemberLogin vo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bevrage_Member> cq = cb.createQuery(Bevrage_Member.class);
		Root<Bevrage_Member> root = cq.from(Bevrage_Member.class);
		// 人員編號
		Predicate identification_NO = cb.equal(root.get("identificationNO"), vo.getId());
		// 查詢條件 Predicate to Array
		List<Predicate> conditionsList = new ArrayList<Predicate>();
		if (!vo.getId().equals("")) {
			conditionsList.add(identification_NO);
		}
		// 放入全部查詢條件
		cq.select(root).where(conditionsList.toArray(new Predicate[] {}));
		// 執行結果
		TypedQuery<Bevrage_Member> query = entityManager.createQuery(cq).setFirstResult(0).setMaxResults(1);
		// 執行結果 to OutVo
		outputMember = outputMember.init();
		for (Bevrage_Member bm :query.getResultList()) {
			outputMember.setId(bm.getIdentificationNO());
			outputMember.setName(bm.getCustomerName());
			outputMember.setPwd(bm.getPassword());
			outputMember.setStatus(bm.getStatus());
		}
		return outputMember;
	}
}
