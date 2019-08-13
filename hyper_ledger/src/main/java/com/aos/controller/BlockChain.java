package com.aos.controller;

import java.util.ArrayList;

public class BlockChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static void addBlock(String transaction) {
		blockchain.add(new Block(transaction, blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Chain: Valid, BlockChain_length: "+blockchain.size()+" New_Transaction: "+transaction+"  New Block Added: "+blockchain.get(blockchain.size()-1).getHash());
	}

	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;

		// loop through blockchain to check hashes:
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			// compare registered hash and calculated hash:
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
//				System.out.println("Current Hashes not equal");
				return false;
			}
			// compare previous hash and registered previous hash
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
//				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	
	public static void addGenesis() {
		blockchain.add(new Block("AOS", "0"));
	}

}
