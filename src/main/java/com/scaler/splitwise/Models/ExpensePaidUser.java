package com.scaler.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpensePaidUser {
    private User user;
    private int Amount;
}
