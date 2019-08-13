package com.aos.controller;

import com.aos.servers.Request;

public class Pattern_1 extends Thread {

	Data data = new Data();
	CreateRequest create = new CreateRequest();
	Request request = new Request();
	private String ip;
	
	public Pattern_1(String operation) {
		this.request.setNum1(10);
		this.request.setNum2(2);
		this.request.setOperation(operation);
		this.ip = data.getSd();
	}

	public void run() 
    { 
        try
        { 	
        	create.sendRequest(ip, request);
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    }
}
