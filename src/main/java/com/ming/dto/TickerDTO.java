package com.ming.dto;

import java.math.BigInteger;

import org.tron.trident.abi.datatypes.Address;
import org.tron.trident.abi.datatypes.Bool;
import org.tron.trident.abi.datatypes.Int;

import lombok.Data;

// struct Ticker {
//     address buyer;
//     uint256 minerLevel;
//     uint256 payAmount;
//     bool isUsed;
//     uint256 multiple;
//     address profitToken;
// }
@Data
public class TickerDTO {
    private String buyer;
    private BigInteger minerLevel;
    private BigInteger payAmount;
    private Boolean isUsed;
    private BigInteger multiple;
    private String profitToken;
}
