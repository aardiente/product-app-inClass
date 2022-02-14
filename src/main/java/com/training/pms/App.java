package com.training.pms;

import com.traing.pms.dao.ProductDAOImpl;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ProductDAOImpl pMan = new ProductDAOImpl();
        ProductApp application = new ProductApp(pMan);
        
        application.startProductApp();
    }  
}
