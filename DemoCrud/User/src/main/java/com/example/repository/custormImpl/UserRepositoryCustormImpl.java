package com.example.repository.custormImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.example.entity.UserEntity;
import com.example.model.UserModel;
import com.example.paging.Pageable;
import com.example.repository.custorm.UserRepositoryCustorm;

@Repository
public class UserRepositoryCustormImpl implements UserRepositoryCustorm {

	@PersistenceContext
	private EntityManager entityManager;

	public List<UserEntity> findAll(UserModel model, Pageable pageable) {
		StringBuilder sql = new StringBuilder("FROM UserEntity AS us");
		sql.append(" WHERE 1=1 ");	
		this.buildQuery(sql, model);
		Query query = entityManager.createQuery(sql.toString());
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getLimit());
		return query.getResultList();
	}
	
	public Long getTotalItems(UserModel model) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM UserEntity AS us");
		sql.append(" WHERE 1=1 ");
		this.buildQuery(sql, model);
		Query query = entityManager.createQuery(sql.toString());	
		return (Long) query.getSingleResult();
	}
	
	private StringBuilder buildQuery(StringBuilder sql,UserModel model) {
		if (StringUtils.isNotBlank(model.getNameRole())) {
			sql.append(" JOIN us.UserRoleEntitys usr JOIN usr.role r ");
			sql.append("AND LOWER(r.name) LIKE '%" + model.getNameRole().toLowerCase() + "%'");
		}
		if (StringUtils.isNotBlank(model.getUserName())) {
			sql.append("AND LOWER(us.userName) LIKE '%" + model.getUserName().toLowerCase() + "%'");
		}
		if (StringUtils.isNotBlank(model.getEmail())) {
			sql.append("AND LOWER(us.email) LIKE '%" + model.getEmail().toLowerCase() + "%'");
		}
		return sql;
	}


	

}
