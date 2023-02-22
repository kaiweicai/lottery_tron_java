/**
 * 
 */
package com.ming.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tron.trident.abi.FunctionReturnDecoder;
import org.tron.trident.abi.datatypes.Function;
import org.tron.trident.abi.datatypes.Type;
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.transaction.TransactionBuilder;
import org.tron.trident.proto.Chain.Transaction;
import org.tron.trident.proto.Response.TransactionExtention;
import org.tron.trident.utils.Numeric;

import lombok.extern.slf4j.Slf4j;

/**
 * @author iven
 *
 */
@Component
@Slf4j
public class ContractAction {
	@Value("${privateKey}")
	private String privateKey;
	@Value("${ownerAddr}")
	private String ownerAddr;
	@Value("${contractAddr}")
	private String contractAddr;

	public String exeFunction(Function function) {
		// main net, using TronGrid
		// ApiWrapper wrapper = ApiWrapper.ofMainnet("hex private key", "API key");
		// Nile test net, using a node from Nile official website
		ApiWrapper wrapper = ApiWrapper.ofNile(privateKey);

		TransactionBuilder builder = wrapper.triggerCall(ownerAddr, contractAddr, function);
		builder.setFeeLimit(1000000000L);
		builder.setMemo("memo test");
		Transaction signedTxn = wrapper.signTransaction(builder.build());

		log.info(signedTxn.toString());
		String ret = wrapper.broadcastTransaction(signedTxn);
		log.info("ret is:{}",ret);
		return ret;
	}

	public List<Type> constCallFunction(Function function) {
		//main net, using TronGrid
		//ApiWrapper wrapper = ApiWrapper.ofMainnet("hex private key", "API key");
		//Nile test net, using a node from Nile official website
        ApiWrapper wrapper = ApiWrapper.ofNile(privateKey);

		TransactionExtention txnExt = wrapper.constantCall(ownerAddr, contractAddr, function);
		String result = Numeric.toHexString(txnExt.getConstantResult(0).toByteArray());
		List<Type> functionResult = FunctionReturnDecoder.decode(result, function.getOutputParameters());
		return functionResult;
	}

}
