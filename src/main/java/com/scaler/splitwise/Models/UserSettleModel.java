package com.scaler.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class UserSettleModel  {
    private String name;
    private int Amount;

//    @Override
//    public int compareTo(UserSettleModel o) {
//        return this.Amount - o.Amount;
//    }
}
