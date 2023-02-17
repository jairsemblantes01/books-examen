package com.distribuida.config;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class Config {

@Produces
@RequestScoped
public EntityManager getSession () {
	Map<String, String> env = System.getenv();
	Map<String, Object> configOverrides = new HashMap<String, Object>();
	for (String envName : env.keySet()) {
		if (envName.contains("db_user")) {
			configOverrides.put("jakarta.persistence.jdbc.user", env.get(envName));
		}
		if (envName.contains("db_password")) {
			configOverrides.put("jakarta.persistence.jdbc.password", env.get(envName));
		}
		if (envName.contains("db_url")) {
			configOverrides.put("jakarta.persistence.jdbc.url", env.get(envName));
		}
	}
	System.out.println("Creando EntityManager");
	System.out.println("db_user: " + env.get("db_user"));
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("base", configOverrides);
	return emf.createEntityManager();
}
}
