package com.aamer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.tx.Contract;

public class TestEventsAndLogs {

	public static void main(String[] args) {
		
		// connect to local geth node 1
    	Quorum quorumNode1 = Quorum.build(new HttpService("http://localhost:22000"));
    	
    	ClientTransactionManager ctm = new ClientTransactionManager(quorumNode1,"0xed9d02e382b34818e88b88a309c7fe71e65f419d", null);
    	
    	Multiply m = null;
    	
    	m = Multiply.load("0x1932c48b2bf8102ba33b4a6b545c32236e342f34", quorumNode1, ctm, Contract.GAS_PRICE, Contract.GAS_LIMIT);
    	
    	try {
			//TransactionReceipt rec = m.multiply(new Uint256(BigInteger.valueOf(7))).get();
    		TransactionReceipt rec = quorumNode1.ethGetTransactionReceipt("0x7e80bc8891a30e1b0c3e3ecbffc1e253ea4adc0a26ade6bb5985e8f07bd4d3ca").send().getResult();
			//TransactionReceipt rec = quorumNode1.ethGetTransactionReceipt("0x84bc17567d6cfe277dc20aceab972c17799cad74f662bcd8e28d74306f55c0b1").send().getResult();
			System.out.println("Transaction hash is :"+rec.getTransactionHash());
			
			processLogs(rec.getLogs());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	}

	private static void processLogs(List<Log> logs) {
		Log log = logs.get(0);
		
		
		Event event = new Event("multiply", Collections.emptyList(), Arrays.asList(new TypeReference<Uint256>(){}));
		
		List<Type> results = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());
		System.out.println("Result is :"+results.get(0).getValue());
		
	}

}
