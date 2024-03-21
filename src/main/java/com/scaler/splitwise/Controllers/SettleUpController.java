package com.scaler.splitwise.Controllers;

import com.scaler.splitwise.DTOs.SettleRequestDTO;
import com.scaler.splitwise.DTOs.SettleResponseDTO;
import com.scaler.splitwise.Models.Transactions;
import com.scaler.splitwise.Services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;
    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleResponseDTO settleUp(SettleRequestDTO settleRequestDTO){
        List<Transactions>transactions= settleUpService.settleUp(settleRequestDTO.getGroupID());
        SettleResponseDTO settleResponseDTO = new SettleResponseDTO();
        settleResponseDTO.setTransactions(transactions);
        return settleResponseDTO;
    }
}
