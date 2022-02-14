package com.traing.pms.dao;

import java.util.ArrayList;

import models.Product;

public class ProductDAOImpl implements ProductDAO
{
	public static ArrayList<Product> dbProductList = new ArrayList<Product>();
	public static int productCount = 0;
	
	
	public void addProduct(Product p) 
	{
		p.setProductId(++productCount);
		dbProductList.add(p);
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Adding Product: " + p + " to DB");	
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
	}

	public boolean deleteProduct(Product p) 
	{
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Deleting Product: " + p + " from DB");	
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		
		dbProductList.remove(p);
		return true;
	}

	public Product searchProductById(int id)
	{
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Searching Product id: " + id + " in DB");	
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		
		return new Product();
	}

	public void updateProduct(int id, String name, int quant, int price) 
	{
		Product tempRef = null;
		
		for(Product p : dbProductList)
		{
			if(p.getProductId() == id)
			{
				tempRef = p;
				break;
			}
		}
		
		if(tempRef != null)
		{
			tempRef.setProductName(name);
			tempRef.setPrice(price);
			tempRef.setQuantityOnHand(quant);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Updating Product: " + tempRef + " in DB");	
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		}
		else
			System.out.println("No product was found. No changes made");

	}

	public Product searchByProductId(int id) 
	{
		for(Product p: dbProductList)
		{
			if(p.getProductId() == id)
				return p;
		}
		
		return null; // Returns null if a product is not found
	}

	public Product searchByProductName(String name) 
	{
		for(Product p: dbProductList)
		{
			if(p.getProductName().equals(name))
				return p;
		}
		return null;
	}

	public void printAllProducts() 
	{
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		for(Product p : dbProductList)
		{
			System.out.println(p);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		}
		
	}

}
