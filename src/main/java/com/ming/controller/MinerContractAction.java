/**
 * 
 */
package com.ming.controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.tron.trident.abi.TypeReference;
import org.tron.trident.abi.datatypes.Address;
import org.tron.trident.abi.datatypes.Function;
import org.tron.trident.abi.datatypes.Type;
import org.tron.trident.abi.datatypes.generated.Uint256;

import com.ming.component.ContractAction;

import lombok.extern.slf4j.Slf4j;

/**
 * @author iven
 *
 */
@Controller
@Slf4j
public class MinerContractAction extends ContractAction {
	@Resource
	private ContractAction contractAction;


	

	/**
	 * 抵押矿机接口
	 * 功能：用户凭借购买的票据进行支付一定的金额获得矿机。
	 * 安全：该接口只可被manager调用
	 * 
	 * @param buyer        购买用户钱包地址
	 * @param tickerIndex  票据编号,由调用者确保唯一
	 * @param payAmount    支付金额
	 * @param usdtAmount   usdt需要支付金额
	 * @param profitAmount 收益数量
	 * @return
	 */
	public String pledgeMiner(String buyer, BigInteger tickerIndex, BigInteger payAmount, BigInteger usdtAmount,
			BigInteger profitAmount) {
		Function function = new Function("pledgeMiner",
				Arrays.asList(new Address(buyer),
						new Uint256(tickerIndex),
						new Uint256(payAmount),
						new Uint256(usdtAmount),
						new Uint256(profitAmount)).stream().collect(Collectors.<Type> toList()),
				Collections.<TypeReference<?>>emptyList());

		return contractAction.exeFunction(function);
	}

	/**
	 * 矿机提现接口claimProfit
	 * 功能：用户挖矿产生的收益被该接口提现到个人账户，产出可以多次提现，每次提现需要支付一定的手续费，手续费会转让到手续费账户。挖矿产生的收益会从一个收益账户转入到个人用户账户。
	 * 安全：该接口只可被manager调用
	 * 
	 * @param userAddress    购买用户钱包地址
	 * @param tickerIndex    票据索引
	 * @param claimAmount    提现金额
	 * @param claimFeeAmount REA手续费
	 * @return
	 */
	public String claimProfit(String userAddress, BigInteger tickerIndex, BigInteger claimAmount,
			BigInteger claimFeeAmount) {
		Function function = new Function(
				"claimProfit", Arrays.asList(new Address(userAddress),
						new Uint256(tickerIndex),
						new Uint256(claimAmount),
						new Uint256(claimFeeAmount)).stream().collect(Collectors.<Type>toList()),
				Collections.<TypeReference<?>>emptyList());

		return contractAction.exeFunction(function);
	}

}
