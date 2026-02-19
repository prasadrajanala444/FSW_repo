package com.klu;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@Autowired
	Service s;
	
	@PostMapping("product")
	public String insertProduct(@RequestBody Product product)
	{
		return s.insertData(product);
	}
	
	@PostMapping("product")
	public List<Product> retrive()
	{
		return s.retrivedata();
	}

}
