package com.demo.service;

import java.util.List;

import com.demo.beans.Product;
import com.demo.beans.User;

public interface ProductService {
	List<Product> getAllProducts(); 
	
	boolean addProduct(String pname, int qty, float price);

	Product getById(int pid);

	boolean updateProduct(Product up);

	boolean deleteProduct(int pid);
}
