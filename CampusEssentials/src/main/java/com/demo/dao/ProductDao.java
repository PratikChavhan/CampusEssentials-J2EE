package com.demo.dao;

import java.util.List;

import com.demo.beans.Product;
import com.demo.beans.User;

public interface ProductDao {
	List<Product> findAll();

	boolean save(Product p);

	Product findById(int pid);

	boolean updateProd(Product up);

	boolean remove(int pid);
	
}
