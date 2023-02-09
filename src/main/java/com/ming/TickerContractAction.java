/**
 * 
 */
package com.ming;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

import org.tron.trident.abi.TypeReference;
import org.tron.trident.abi.datatypes.Address;
import org.tron.trident.abi.datatypes.Function;
import org.tron.trident.abi.datatypes.generated.Uint256;

/**
 * @author iven
 *
 */
public class TickerContractAction extends ContractAction {
	private static final Logger logger = Logger.getLogger(TickerContractAction.class.getName());

	/**
	 * 购买票据接口buyTicker
	 * 功能：接口收取用户的REA token 手续费后，给用户生成一张购买矿机的票据。
	 * 安全：该接口只有管理员能够调用
	 * @param buyer	购买用户钱包地址
	 * @param tickerIndex	票据编号,由调用者确保唯一
	 * @param minerLevel	矿机等级
	 * @param tickerPayAmount	票据购买手续费
	 * @param multiple	矿机倍数
	 * @param profitToken	挖矿产出token
	 * @return
	 */
	public String buyTicker(String buyer, BigInteger tickerIndex, BigInteger minerLevel, BigInteger tickerPayAmount,
			BigInteger multiple, String profitToken) {
		Function function = new Function("buyTicker", Arrays.asList(
				new Address(buyer), 			// buyer
				new Uint256(tickerIndex), 		// tickerIndex
				new Uint256(minerLevel), 		// minerLevel
				new Uint256(tickerPayAmount), 	// tickerPayAmount
				new Uint256(multiple), 			// multiple
				new Address(profitToken) 		// profitToken
		), Collections.<TypeReference<?>>emptyList());

		return exeFunction(function);
	}
	
	/**
	 * 票据及时奖励接口rewardTicker
     * 功能：用户调用该接口，不需要支付，直接从奖励账户奖励N-1的REA token给用户。
     * 安全：该接口只可被manager调用
	 * @param buyer	购买用户钱包地址
	 * @param payAmount	用户支付金额
	 * @return
	 */
	public String rewardTicker(String buyer, BigInteger payAmount) {
		Function function = new Function("rewardTicker", Arrays.asList(
				new Address(buyer), 			
				new Uint256(payAmount)		
		), Collections.<TypeReference<?>>emptyList());

		return exeFunction(function);
	}
	
	private String exeFunction(Function function) {
		//用配置文件取代
		String privateKey = "d6c12ee57f6a0bbaeb823a4c34e61fe2d2da0557a392392b979ab46c97c2cc5f";
		String ownerAddr = "TEeeCkMA3gXekaKRPYMhhEwUkve6YBCTVy";  
		String contractAddr = "TVVYiR89Ty7FXWPi7jfjYBMNkmZvVwtse3";
		
		return exeFunction(function,privateKey,ownerAddr,contractAddr);
	}

}
