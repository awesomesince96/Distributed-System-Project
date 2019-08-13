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
	
	Boolean isChainValid = true;
	BlockChain blockchain = new BlockChain();


	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "GetServerIdentityRequest")
	@ResponsePayload
	public GetServerIdentityResponse processIdentity(@RequestPayload GetServerIdentityRequest request) {
		GetServerIdentityResponse response = new GetServerIdentityResponse();
		ServerIdentity serverDetails = new ServerIdentity();
		response.setId(1);
		return response;
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Result")
	@ResponsePayload
	public void processIdentity(@RequestPayload Result transaction) {
		if(isChainValid) {
			boolean valid = blockchain.isChainValid();
			if(true) {
				blockchain.addBlock(transaction.getResult());
			}else{
				isChainValid = false;
				System.out.println("CHAIN HAS BEEN BROKEN. System Breached.");
			}
		}
	}

}
