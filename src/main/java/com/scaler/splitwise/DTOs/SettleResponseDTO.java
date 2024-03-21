package com.scaler.splitwise.DTOs;

import com.scaler.splitwise.Models.Transactions;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SettleResponseDTO {
    private List<Transactions>transactions;
}
