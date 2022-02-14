package com.training.pms;
import java.util.Scanner;
import com.traing.pms.dao.ProductDAO;
import com.traing.pms.dao.ProductDAOImpl;
import com.training.pms.InputWrapper.InputStatus;

import models.Product;

class InputWrapper
{
	protected String name;
	protected int quant;
	protected int price;
	
    protected InputStatus status = InputStatus.Name;
	
	protected enum InputStatus
	{
		Name, Quantity, Price, Exit
	}
}


public class ProductApp
{
	Scanner scanner = new Scanner(System.in);
	ProductDAO dbLogicHandle;
	
	public ProductApp(ProductDAOImpl handle)
	{
		dbLogicHandle = handle;
	}
	
	
	public void startProductApp()
	{
		boolean exitFlag = false;
		
		do
		{
			printMenuOptions();
			
			if(scanner.hasNextInt())
			{
				int arg = scanner.nextInt();
				
				switch(arg)
				{
				case 1:
					this.addLogic(); 
					break;
				case 2:
					this.deleteLogic();
					break;
				case 3:
					this.updateLogic();
					break;
				case 4:
					this.searchByIdLogic();
					break;
				case 5:
					this.searchByNameLogic();
					break;
				case 6:
					dbLogicHandle.printAllProducts();
					break;
				case 9:
					exitFlag = true;
					System.out.println("Exiting program! Bye Bye!");
					System.exit(0);
					break;
				default:
						System.out.println("Invalid input, please try again.");
				}
			}
			else
			{
				System.out.println("Invalid input, please try again.");
				scanner.next();
			}

		}while(!exitFlag);
	}
	
	
	// Helper methods
	private InputWrapper getProductDetailsFromUser()
	{
		InputWrapper temp = new InputWrapper();
		
		
		System.out.println("Please enter product info:");
		
		do
		{
			switch(temp.status)
			{
				case Name:
					System.out.println("Enter name: ");
					
					temp.name = scanner.next();
					temp.status = InputStatus.Quantity;
					break;
					
				case Quantity:
					System.out.println("Enter quantity: ");
					if(scanner.hasNextInt())
					{
						temp.quant = scanner.nextInt();
						temp.status = InputStatus.Price;
					}
					else
					{
						System.out.println("Invalid input, please enter a number");
						scanner.next();
					}
					
					break;
					
				case Price:
					System.out.println("Enter price: ");
					if(scanner.hasNextInt())
					{
						temp.price = scanner.nextInt();
						temp.status = InputStatus.Exit;
					}
					else
					{
						System.out.println("Invalid input, please enter a number");
						scanner.next();
					}
					break;
			
				default:
					System.out.println("invalid... how did you get here...");
			}
		}while(temp.status != InputStatus.Exit);
		
		return temp;
	}
	private int getUserIdFromUser()
	{
		int id = -1;
		boolean flag = false; // unnecessary but its here for now. Fix it.
		
		System.out.println("Please give the Id of the product you want");
		do
		{
			if(scanner.hasNextInt())
			{
				id = scanner.nextInt();
				flag = true;
			}
			else
			{
				System.out.println("Invalid input, please enter a numerical id");
				scanner.next();
			}
			
		}while(!flag);
		
		return id;
	}
	
	
	private void addLogic()
	{
		int id = -1;

		InputWrapper input = getProductDetailsFromUser();
		
		dbLogicHandle.addProduct(new Product(id, input.name, input.quant, input.price));
	}
	
	private void deleteLogic()
	{	
		int id = getUserIdFromUser();
		
		Product markedForDeletion = dbLogicHandle.searchByProductId(id);
		
		dbLogicHandle.deleteProduct(markedForDeletion);
	}
	
	private void updateLogic()
	{
		int id = getUserIdFromUser();

		InputWrapper input = getProductDetailsFromUser();
		
		dbLogicHandle.updateProduct(id, input.name, input.quant, input.price);
	}
	
	private void searchByIdLogic()
	{
		int id = getUserIdFromUser();
		
		Product foundObj = dbLogicHandle.searchByProductId(id);
		
		if(foundObj != null)
			System.out.println(foundObj);
		else
			System.out.println("Nothing was found with that id");
	}
	private void searchByNameLogic()
	{
		System.out.println("Please give the name of the product you want");
		
		String name = scanner.next();
		Product foundObj = dbLogicHandle.searchByProductName(name);
		
		if(foundObj != null)
			System.out.println(foundObj);
		else
			System.out.println("Nothing was found by that name");
	}

	public void printMenuOptions()
	{
		System.out.println("P R O D U C T - APP M E N U");
		System.out.println("1. Add Product");
		System.out.println("2. Delete Product");
		System.out.println("3. Update Product");
		System.out.println("4. Search by Id Product");
		System.out.println("5. Search by Name Product");
		System.out.println("6. Print All Products");
		System.out.println("9. Exit");
	}
	
	@Override
	public void finalize()
	{
		this.scanner.close();
		System.out.println("Closed scanner on deletion");
	}
}
