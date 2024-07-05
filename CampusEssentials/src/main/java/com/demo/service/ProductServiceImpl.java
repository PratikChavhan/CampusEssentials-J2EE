package com.demo.service;

import java.util.List;

import com.demo.beans.Product;
import com.demo.beans.User;
import com.demo.dao.ProductDao;
import com.demo.dao.ProductDaoImpl;

public class ProductServiceImpl implements ProductService{
	
	ProductDao pdao = new ProductDaoImpl();

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return pdao.findAll();
	}

	@Override
	public boolean addProduct(String pname, int qty, float price) {
		// TODO Auto-generated method stub
		ProductDao pdao = new ProductDaoImpl();
		
		if (pdao.save(new Product(pname, qty, price))) {
			return true;
		}
		return false;
	}

	@Override
	public Product getById(int pid) {
		return pdao.findById(pid);
	}

	@Override
	public boolean updateProduct(Product up) {
		// TODO Auto-generated method stub
		return pdao.updateProd(up);
	}

	@Override
	public boolean deleteProduct(int pid) {
		// TODO Auto-generated method stub
		return pdao.remove(pid);
	}

}
