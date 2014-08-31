package com.tonyli.recipefinder.dao;

import java.util.UUID;

import org.hibernate.Session;

public class AbstractDAO {
    protected Session session ;

    public AbstractDAO(Session session) {
        this.session = session;
    }

    public static String generateUUID(){
        return new UUID(System.currentTimeMillis(), System.nanoTime()).toString();
    }
}
