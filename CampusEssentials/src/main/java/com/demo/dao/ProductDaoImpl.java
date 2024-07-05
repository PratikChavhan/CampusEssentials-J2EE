package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.demo.beans.Product;
import com.demo.beans.User;

public class ProductDaoImpl implements ProductDao{
	static Connection conn;
	static PreparedStatement allProducts, insertProduct, findProduct, updateProduct, deleteProduct;
	
	static {
		try {
			conn = DBUtil.getMyConnection();
			allProducts = conn.prepareStatement("select pid, pname, qty, price from product");
			insertProduct = conn.prepareStatement("insert into product(pname, qty, price) value(?, ?, ?)");
			findProduct = conn.prepareStatement("select pid, pname, qty, price from product where pid = ?");
			updateProduct = conn.prepareStatement("update product set pname = ?, qty = ?, price = ? where pid = ?");
			deleteProduct = conn.prepareStatement("delete from product where pid = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		try {
			List<Product> plist = new ArrayList<>();
			ResultSet rs = allProducts.executeQuery();
			while(rs.next()) {
				plist.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
			return plist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean save(Product p) {
		// TODO Auto-generated method stub
		try {
			insertProduct.setString(1, p.getPname());
			insertProduct.setInt(2, p.getQty());
			insertProduct.setDouble(3, p.getPrice());
			int check = insertProduct.executeUpdate();
			return check > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(int pid) {
		try {
			Product product;
			findProduct.setInt(1, pid);
			ResultSet rs = findProduct.executeQuery();
			while(rs.next()) {
				product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateProd(Product up) {
		// TODO Auto-generated method stub
		try {
			updateProduct.setString(1, up.getPname());
			updateProduct.setInt(2, up.getQty());
			updateProduct.setDouble(3, up.getPrice());
			updateProduct.setInt(4, up.getPid());
			
			int check = updateProduct.executeUpdate();
			
			return check > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean remove(int pid) {
		// TODO Auto-generated method stub
		try {
			deleteProduct.setInt(1, pid);
			int check = deleteProduct.executeUpdate();
			return check > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
