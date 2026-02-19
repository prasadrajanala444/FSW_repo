package com.klu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
@org.springframework.stereotype.Service
public class Service {
	
	@Autowired
	Repo r;

	public String insertData(Product product) {
		r.save(product);
		return "Data saved successfully";
	}

	public List<Product> retrivedata() {
		return r.findAll();
	}

}
