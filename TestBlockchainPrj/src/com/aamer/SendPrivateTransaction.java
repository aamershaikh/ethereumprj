package com.aamer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.methods.request.PrivateTransaction;
import org.web3j.quorum.tx.ClientTransactionManager;

public class SendPrivateTransaction {

		public TransactionReceipt receipt;
		BigInteger nonce = new BigInteger("0x0");
		BigInteger gas = new BigInteger("300000");
		BigInteger gasLimit = new BigInteger("600000");
		

	    public static void main(String[] args) {
	    	
	    	// connect to local geth node 1
	    	Quorum quorumNode1 = Quorum.build(new HttpService("http://localhost:22001"));
	    	
	    	List<String> list = new ArrayList<String>();
			list.add("R56gy4dn24YOjwyesTczYa8m5xhP6hF2uTMCju/1xkY=");
			
			ClientTransactionManager ctm = new ClientTransactionManager(quorumNode1,"0x0fbdc686b912d7722dc86510934589e0aaf3b55a", list);
	    	
	    	Simplestorage ss;
			try {
				ss = Simplestorage.deploy(quorumNode1, ctm, new BigInteger("300000"), new BigInteger("300000"), new BigInteger("0"), new Uint256(new BigInteger("10101"))).get();
				ss.set(new Uint256(new BigInteger("2819")));
				
				System.out.println("value in same node is :"+ss.get().get(60, TimeUnit.SECONDS));
				
				fetchSameValueInNode5(ss, ss.getContractAddress());
				
				System.out.println("result contract addr: "+ss.getContractAddress()+ ","+"transaction hash: "+ss.getTransactionReceipt().get().getTransactionHash());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    private static void fetchSameValueInNode5(Simplestorage ss, String contractAddress) {
			Quorum quorumNode4 = Quorum.build(new HttpService("http://localhost:22004"));
			
			 List<Type> inputParameters = new ArrayList<Type>();
			 List<TypeReference<?>> outputParameters = new ArrayList<TypeReference<?>>();
			 outputParameters.add(new TypeReference<Uint256>(){});
			 
			 Function function = new Function("get", inputParameters, outputParameters);
			 String encodedFunction = FunctionEncoder.encode(function);
			 try {
				org.web3j.protocol.core.methods.response.EthCall response = quorumNode4.ethCall(
				         Transaction.createEthCallTransaction("0x0fbdc686b912d7722dc86510934589e0aaf3b55a", contractAddress, encodedFunction),
				         DefaultBlockParameterName.LATEST)
				         .sendAsync().get();
				
				List<Type> someTypes = FunctionReturnDecoder.decode(
			            response.getValue(), function.getOutputParameters());
				
				System.out.println("response is: "+someTypes.get(0).getValue());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
}
	


