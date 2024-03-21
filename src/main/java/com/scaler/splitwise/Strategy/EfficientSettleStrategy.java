package com.scaler.splitwise.Strategy;

import com.scaler.splitwise.Models.Transactions;
import com.scaler.splitwise.Models.UserSettleModel;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EfficientSettleStrategy implements SettleStrategy {
    @Override
    public List<Transactions> settleUp(HashMap<String, Integer> balanceSheet) {
        List<Transactions>ans = new ArrayList<>();
        PriorityQueue<UserSettleModel>positiveUser = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<UserSettleModel>NegativeUser = new PriorityQueue<>();

        for ( String user : balanceSheet.keySet()){
            if ( balanceSheet.get(user) > 0 ){
                UserSettleModel usm = new UserSettleModel();
                usm.setName(user);
                usm.setAmount(balanceSheet.get(user));
                positiveUser.add(usm);
            }
            else if (balanceSheet.get(user) < 0 ) {
                UserSettleModel usm = new UserSettleModel();
                usm.setName(user);
                usm.setAmount(balanceSheet.get(user));
                NegativeUser.add(usm);
            }
        }

        while (!NegativeUser.isEmpty() && !positiveUser.isEmpty()){
            UserSettleModel positive = positiveUser.remove();
            UserSettleModel negative = NegativeUser.remove();

            int amount = Math.min(positive.getAmount(), negative.getAmount());

            Transactions transactions = new Transactions();
            transactions.setFrom(negative.getName());
            transactions.setTo(positive.getName());
            transactions.setAmount(amount);
            ans.add(transactions);

            positive.setAmount(positive.getAmount()-amount);
            negative.setAmount(negative.getAmount()+amount);

            if ( positive.getAmount() > 0 ){
                positiveUser.add(positive);
            }
            if ( negative.getAmount() < 0 ){
                NegativeUser.add(negative);
            }
        }
        return ans;
    }
}
