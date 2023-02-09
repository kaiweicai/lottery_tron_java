/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ming;

import java.math.BigInteger;

import org.junit.Test;

public class ContractTest {
	
	TickerContractAction ticker = new TickerContractAction();
	MinerContractAction miner = new MinerContractAction();
	
	//@Test
	public void testBuyTicker() {
		String ret = ticker.buyTicker("TEeeCkMA3gXekaKRPYMhhEwUkve6YBCTVy", BigInteger.valueOf(1000), BigInteger.valueOf(3),
				BigInteger.valueOf(100), BigInteger.valueOf(3), "TVhTnr8TqheP2My6UNtuFze1mSw2B9pUW6");
		System.out.println(ret);
	}

	//@Test
	public void testRewardTicker() {
		String ret = ticker.rewardTicker("TEeeCkMA3gXekaKRPYMhhEwUkve6YBCTVy", BigInteger.valueOf(100));
		System.out.println(ret);
	}
	
	//@Test
	public void testPledgeMiner() {
		String ret = miner.pledgeMiner("TEeeCkMA3gXekaKRPYMhhEwUkve6YBCTVy", BigInteger.valueOf(1000),
				BigInteger.valueOf(100), BigInteger.valueOf(100), BigInteger.valueOf(60));
		System.out.println(ret);
	}
	
	@Test
	public void testClaimProfit() {
		String ret = miner.claimProfit("TEeeCkMA3gXekaKRPYMhhEwUkve6YBCTVy", BigInteger.valueOf(1000),
				BigInteger.valueOf(1000), BigInteger.valueOf(300));
		System.out.println(ret);
	}

}
