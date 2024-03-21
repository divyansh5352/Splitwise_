package com.scaler.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transactions {
    private String from;
    private String to;
    private int Amount;

    public Transactions(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        Amount = amount;
    }
    public Transactions(){}

    @Override
    public String toString() {
        return from+" should pay "+Amount+" rupees to "+to;
    }
}
