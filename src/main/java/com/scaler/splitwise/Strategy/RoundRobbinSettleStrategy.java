package com.scaler.splitwise.Strategy;

import com.scaler.splitwise.Models.Transactions;
import com.scaler.splitwise.Models.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoundRobbinSettleStrategy implements SettleStrategy{
    @Override
    public List<Transactions> settleUp(HashMap<String, Integer> balanceSheet) {
        List<Transactions>ans = new ArrayList<>();

        ArrayList<String> users = new ArrayList<>(balanceSheet.keySet());

        for ( int i = 0 ; i < users.size()-1 ; i++ ){
            String currentUser = users.get(i);
            String nextUser = users.get(i+1);

            int currentBalance = balanceSheet.get(currentUser);
            int nextBalance = balanceSheet.get(nextUser);

            Transactions t = null;
            if ( currentBalance > 0){
                t = new Transactions( nextUser , currentUser , currentBalance);
            }
            else if ( currentBalance < 0 ){
                t = new Transactions(currentUser , nextUser , -currentBalance);
            }

            balanceSheet.put(currentUser , 0);
            balanceSheet.put(nextUser , nextBalance+currentBalance);

            if ( t != null ){
                ans.add(t);
            }
        }

        return ans;
    }
}
