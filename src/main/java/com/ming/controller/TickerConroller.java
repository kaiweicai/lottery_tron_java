/**
 * 
 */
package com.ming.controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tron.trident.abi.TypeReference;
import org.tron.trident.abi.datatypes.Address;
import org.tron.trident.abi.datatypes.Bool;
import org.tron.trident.abi.datatypes.Function;
import org.tron.trident.abi.datatypes.Type;
import org.tron.trident.abi.datatypes.Utf8String;
import org.tron.trident.abi.datatypes.Address;
import org.tron.trident.abi.datatypes.Int;
import org.tron.trident.abi.datatypes.generated.Uint256;

import com.ming.component.ContractAction;
import com.ming.dto.TickerDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author iven
 *
 */
@Slf4j
@RestController("AppLocationController")
@RequestMapping("/ticker")
public class TickerConroller extends ContractAction {

	@Value("${privateKey}")
	private String privateKey;

	@Autowired
	private ContractAction contractAction;

	@GetMapping("/userTicker")
	public TickerDTO myTicker(String userAddress, BigInteger tickerIndex) {
		Function getUserTick = new Function("getUserTick",
				Arrays.asList(
						new Address(userAddress), // buyer
						new Uint256(tickerIndex)).stream().collect(Collectors.<Type>toList()),
				Arrays.asList(new TypeReference<Address>() {
				}, new TypeReference<Int>() {
				}, new TypeReference<Int>() {
				}, new TypeReference<Bool>() {
				}, new TypeReference<Int>() {
				}, new TypeReference<Address>() {
				}).stream().collect(Collectors.<TypeReference<?>>toList()));
		// address buyer;
		// uint256 minerLevel;
		// uint256 payAmount;
		// bool isUsed;
		// uint256 multiple;
		// address profitToken;
		List<Type> callResult = contractAction.constCallFunction(getUserTick);
		log.info("call result is:{}",callResult.toString());
		TickerDTO ticker = new TickerDTO();
		ticker.setBuyer((String)callResult.get(0).getValue());
		ticker.setMinerLevel((BigInteger)(callResult.get(1).getValue()));
		ticker.setPayAmount((BigInteger)(callResult.get(2).getValue()));
		ticker.setIsUsed((Boolean)(callResult.get(3).getValue()));
		ticker.setMultiple((BigInteger)(callResult.get(4).getValue()));
		ticker.setProfitToken((String)callResult.get(5).getValue());
		return ticker;
	}

	/**
	 * ??????????????????buyTicker
	 * ??????????????????????????????REA token ????????????????????????????????????????????????????????????
	 * ?????????????????????????????????????????????
	 * 
	 * @param buyer           ????????????????????????
	 * @param tickerIndex     ????????????,????????????????????????
	 * @param minerLevel      ????????????
	 * @param tickerPayAmount ?????????????????????
	 * @param multiple        ????????????
	 * @param profitToken     ????????????token
	 * @return
	 */
	@GetMapping("/buyTicker")
	public String buyTicker(String buyer, BigInteger tickerIndex, BigInteger minerLevel, BigInteger tickerPayAmount,
			BigInteger multiple, String profitToken) {
		Function function = new Function("buyTicker", Arrays.asList(
				new Address(buyer), // buyer
				new Uint256(tickerIndex), // tickerIndex
				new Uint256(minerLevel), // minerLevel
				new Uint256(tickerPayAmount), // tickerPayAmount
				new Uint256(multiple), // multiple
				new Address(profitToken) // profitToken
		).stream().collect(Collectors.<Type>toList()), Collections.<TypeReference<?>>emptyList());

		return contractAction.exeFunction(function);
	}

	/**
	 * ????????????????????????rewardTicker
	 * ??????????????????????????????????????????????????????????????????????????????N-1???REA token????????????
	 * ???????????????????????????manager??????
	 * 
	 * @param buyer     ????????????????????????
	 * @param payAmount ??????????????????
	 * @return
	 */
	@GetMapping("/rewardTicker")
	public String rewardTicker(String buyer, BigInteger payAmount) {
		Function function = new Function("rewardTicker", Arrays.asList(
				new Address(buyer),
				new Uint256(payAmount)).stream().collect(Collectors.<Type>toList()),
				Collections.<TypeReference<?>>emptyList());

		return contractAction.exeFunction(function);
	}

	@GetMapping("/")
	public String test() {
		log.info("privateKey is:{}", privateKey);
		return "abc";
	}

}
