package com.wellsfargo.bankapp.mapper;

import com.wellsfargo.bankapp.dto.FDAccountDTO;
import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.entity.account.FDAccount;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class FDAccountDTOMapper implements Function<FDAccount, FDAccountDTO> {
    @Override
    public FDAccountDTO apply(FDAccount fdAccount) {
        FDAccountDTO fdAccountDTO = new FDAccountDTO();
        fdAccountDTO.setId(fdAccount.getId());
        fdAccountDTO.setPrincipalAmount(fdAccount.getPrincipalAmount());
        fdAccountDTO.setMaturityPeriod(fdAccount.getMaturityPeriod());
        return fdAccountDTO;
    }
}