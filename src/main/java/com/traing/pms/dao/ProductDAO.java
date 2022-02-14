package com.traing.pms.dao;

import models.Product;

public interface ProductDAO 
{
	public void addProduct(Product p);
	public boolean deleteProduct(Product p);
	public void updateProduct(int id, String name, int quant, int price);
	public Product searchByProductId(int id);
	public Product searchByProductName(String name);
	public void printAllProducts();
}
