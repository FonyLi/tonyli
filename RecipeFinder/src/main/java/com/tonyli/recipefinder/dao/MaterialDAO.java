package com.tonyli.recipefinder.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class MaterialDAO extends AbstractDAO{
private static Logger logger = Logger.getLogger(MaterialDAO.class.getName());
	
	public MaterialDAO(Session session) {
		super(session);
	}
}
