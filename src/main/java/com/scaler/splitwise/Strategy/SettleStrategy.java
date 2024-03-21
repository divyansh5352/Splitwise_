package com.scaler.splitwise.Strategy;

import com.scaler.splitwise.Models.Transactions;

import java.util.HashMap;
import java.util.List;
public interface SettleStrategy {
    public List<Transactions> settleUp(HashMap<String,Integer>balanceSheet);
}
