package com.scaler.splitwise.Services;

import com.scaler.splitwise.Models.*;
import com.scaler.splitwise.Repositories.GroupRepository;
import com.scaler.splitwise.Strategy.EfficientSettleStrategy;
import com.scaler.splitwise.Strategy.SettleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private SettleStrategy settleStrategy;
    private GroupRepository groupRepository;
    @Autowired
    public SettleUpService(EfficientSettleStrategy efficientSettleStrategy, GroupRepository groupRepository) {
        this.settleStrategy = efficientSettleStrategy;
        this.groupRepository = groupRepository;
    }

    public List<Transactions> settleUp( Long GroupId ){
        HashMap<String,Integer>map = new HashMap<>();
        Optional<Group> optionalGroup = groupRepository.findbyId(GroupId);
        Group group = optionalGroup.get();

        List<Expense> expenses = group.getExpenses();
        List<ExpensePaidUser>expensePaidUsers = new ArrayList<>();
        List<ExpenseSharedUser>expenseSharedUsers = new ArrayList<>();

        for ( Expense e : expenses){
            List<ExpensePaidUser>epu = e.getPaidUsers();
            for ( ExpensePaidUser pu : epu ){
                map.put( pu.getUser().getName(),
                        map.getOrDefault(pu.getUser().getName() , 0 )+pu.getAmount());
            }
            List<ExpenseSharedUser>esu = e.getSharedUsers();
            for( ExpenseSharedUser su : esu ){
                map.put(su.getUser().getName() ,
                        map.getOrDefault(su.getUser().getName() , 0 )- su.getAmount());
            }
        }

        return settleStrategy.settleUp(map);
    }
}

/*
400
paid by
A->200
B->200

shared by
A->100
B->100
C->100
d->100

600
paid by
A->200
D->400

shared by
A->150
B->150
C->150
D->150

balanced sheet

A: +200-100+200-150 = 150    0
B: +200-100-150     = -50    0
C: -100-150         = -250   0
D: -100+400-150     = 150    0

B->A : 150
C->B : 100
c->D : 150






 */
