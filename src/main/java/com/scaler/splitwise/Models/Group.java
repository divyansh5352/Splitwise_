package com.scaler.splitwise.Models;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group extends BaseClass{
    private String Name;
    private List<User>Members;
    private User Admin;
    private List<Expense>expenses;
}
