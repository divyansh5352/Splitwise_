package com.scaler.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Expense extends BaseClass{
    private String Description;
    private int TotalAmount;
    private List<ExpensePaidUser>paidUsers;
    private List<ExpenseSharedUser>sharedUsers;
}
