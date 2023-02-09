/**
 * 
 */
package com.ming;

import java.util.logging.Logger;

import org.tron.trident.abi.datatypes.Function;
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.transaction.TransactionBuilder;
import org.tron.trident.proto.Chain.Transaction;

/**
 * @author iven
 *
 */
public abstract class ContractAction {
	
	private static final Logger logger = Logger.getLogger(ContractAction.class.getName());
	
	protected String exeFunction(Function function, String privateKey, String ownerAddr, String contractAddr) {
		//main net, using TronGrid
		//ApiWrapper wrapper = ApiWrapper.ofMainnet("hex private key", "API key");
		//Nile test net, using a node from Nile official website
        ApiWrapper wrapper = ApiWrapper.ofNile(privateKey);
        
        TransactionBuilder builder = wrapper.triggerCall(ownerAddr, contractAddr, function);
        builder.setFeeLimit(1000000000L);
        builder.setMemo("memo test");
        Transaction signedTxn = wrapper.signTransaction(builder.build());

        logger.info(signedTxn.toString());
        String ret = wrapper.broadcastTransaction(signedTxn);
        
        return ret;
	}
	
}
