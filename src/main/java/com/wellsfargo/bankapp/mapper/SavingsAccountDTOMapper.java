package com.wellsfargo.bankapp.mapper;

import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SavingsAccountDTOMapper implements Function<SavingsAccount, SavingsAccountDTO> {
    @Override
    public SavingsAccountDTO apply(SavingsAccount savingsAccount) {
        SavingsAccountDTO savingsAccountDTO = new SavingsAccountDTO();
        savingsAccountDTO.setId(savingsAccount.getId());
        savingsAccountDTO.setBalance(savingsAccount.getBalance());
        savingsAccountDTO.setHasCreditCard(savingsAccount.getHasCreditCard());
        savingsAccountDTO.setHasDebitCard(savingsAccount.getHasDebitCard());
        return savingsAccountDTO;
    }
}
