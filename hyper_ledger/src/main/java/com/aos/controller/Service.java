package com.aos.controller;

public class Service {

	BlockChain blockchain = new BlockChain();
	
	@SuppressWarnings("static-access")
	public void startConnection() {
		blockchain.addGenesis();
	}
}
