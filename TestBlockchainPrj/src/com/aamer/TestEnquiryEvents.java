package com.aamer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.tx.Contract;

public class TestEnquiryEvents {

	public static void main(String[] args) {

		// connect to local geth node 1
		Quorum quorumNode1 = Quorum.build(new HttpService("http://localhost:22001"));

		List<String> list = new ArrayList<String>();

		ClientTransactionManager ctm = new ClientTransactionManager(quorumNode1,
				"0x0fbdc686b912d7722dc86510934589e0aaf3b55a", null);

		EnquiryDetailsEvents ede;
		try {
			/*ede = EnquiryDetailsEvents.load("0x3b0f26b0a365e7028bbda35e5438c7763c0d8a99", quorumNode1, ctm, Contract.GAS_PRICE, Contract.GAS_LIMIT);
			TransactionReceipt rec = ede.storeEnquiry(new Uint256(BigInteger.valueOf(9)), new Uint256(BigInteger.valueOf(7)),
					new Utf8String("PENDING"), new Utf8String("1"), new Utf8String("2"), new Utf8String("ABCD")).get();

			System.out.println("Transaction Receipt :"+rec.getTransactionHash());
			System.out.println("value in same node is :" + ede.getEnquiryDetails().get(60, TimeUnit.SECONDS).get(5).getValue());
			
			TransactionReceipt rec = quorumNode1.ethGetTransactionReceipt("0x54d96d73c6b519c9d5e04ca6f67fc1132bf4938991a23d13c5feab448bd16ba1").send().getResult();
			processLogs(rec.getLogs());*/
			
			compareStrings();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void compareStrings() {
		String s1= "aamer";
		String s2 = "aamer";
		String s3 = "aamer";
		
		List<String> l = Arrays.asList(s1,s2,s3);
		if (l.stream().distinct().count() == 1){
			System.out.println("Equal");
		} else {
			System.out.println("unequal");
		}
		
	}


	private static void processLogs(List<Log> logs) {
		Log log = logs.get(0);

		Event event = new Event("Enquiry", Collections.emptyList(), Arrays.asList(
		   new TypeReference<Uint256>() {
		}, new TypeReference<Uint256>() {
		}, new TypeReference<Utf8String>() {
		}, new TypeReference<Utf8String>() {
		}, new TypeReference<Utf8String>() {
		}, new TypeReference<Utf8String>() {
		}));

		List<Type> results = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());
		for (int i = 0; i < 6; i++) {
			System.out.println("Result is :" + results.get(i).getValue());
		}

	}
}
