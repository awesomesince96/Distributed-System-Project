package com.aos.controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.aos.servers.Add;
import com.aos.servers.GetServerIdentityRequest;
import com.aos.servers.GetServerIdentityResponse;
import com.aos.servers.Multi;
import com.aos.servers.Result;
import com.aos.servers.ServerIdentity;
import com.aos.servers.Sub;

@Endpoint
public class ServerEndpoint {
	String ip;
	int base_time = 3000;
	ClientManager manager = new ClientManager();

	void serverendpoint() {
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "GetServerIdentityRequest")
	@ResponsePayload
	public GetServerIdentityResponse processIdentity(@RequestPayload GetServerIdentityRequest request) {
		GetServerIdentityResponse response = new GetServerIdentityResponse();
		ServerIdentity serverDetails = new ServerIdentity();
		response.setId(1);
		return response;
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Add")
	@ResponsePayload
	public Result processIdentity(@RequestPayload Add request) {
		ServerLoad.load += 1;
		Result response = new Result();
		float answer = 0;
		int sleep_time = getSleepTime();
		manager.sendTransaction("Add");
		try {
			Thread.sleep(base_time + sleep_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		answer = request.getNum1() + request.getNum2();
		response.setResult(Float.toString(answer));
		System.out.println("Add: "+request.getNum1()+" + "+request.getNum2()+" = "+answer);
		ServerLoad.load -= 1;
		return response;
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Sub")
	@ResponsePayload
	public Result processIdentity(@RequestPayload Sub request) {
		ServerLoad.load += 1;
		Result response = new Result();
		float answer = 0;
		int sleep_time = getSleepTime();
		manager.sendTransaction("Sub");
		try {
			Thread.sleep(base_time + sleep_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		answer = request.getNum1() - request.getNum2();
		response.setResult(Float.toString(answer));
		System.out.println("Sub: "+request.getNum1()+" - "+request.getNum2()+" = "+answer);
		ServerLoad.load -= 1;
		return response;
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Multi")
	@ResponsePayload
	public Result multi(@RequestPayload Multi request) {
		ServerLoad.load += 1;
		Result response = new Result();
		float answer = 0;
		int sleep_time = getSleepTime();
		manager.sendTransaction("Multi");
		try {
			Thread.sleep(base_time + sleep_time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		answer = request.getNum1() * request.getNum2();
		response.setResult(Float.toString(answer));
		System.out.println("Multi: "+request.getNum1()+" x "+request.getNum2()+" = "+answer);
		ServerLoad.load -= 1;
		return response;
	}

	public int getSleepTime() {
		int sleep_time = 0;
		if (ServerLoad.load < ServerLoad.medium) {
			sleep_time = ServerLoad.under_time;
		}
		if (ServerLoad.load >= ServerLoad.medium && ServerLoad.load < ServerLoad.overload) {
			sleep_time = ServerLoad.under_time;
		}
		if (ServerLoad.load < ServerLoad.overload) {
			sleep_time = ServerLoad.under_time;
		}
		return sleep_time;
	}

}
