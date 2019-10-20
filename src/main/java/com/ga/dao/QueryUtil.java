package com.ga.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ga.entity.UserRole;

public class QueryUtil {
	public Query fetchUserRole(Session session, String roleName) {
		return session.createQuery("FROM UserRole r WHERE r.name = '" + 
				roleName + "'");
	}
}
