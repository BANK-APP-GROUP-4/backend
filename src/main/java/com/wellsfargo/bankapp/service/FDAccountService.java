package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.dto.FDAccountDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.FDAccount;
import com.wellsfargo.bankapp.exception.FDAccountNotFoundException;
import com.wellsfargo.bankapp.mapper.FDAccountDTOMapper;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FDAccountService {
    @Autowired
    private final FDAccountRepo fdAccountRepo;
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final FDAccountDTOMapper fdAccountDTOMapper;

    public FDAccountService(
            FDAccountRepo fdAccountRepo,
            CustomerService customerService,
            FDAccountDTOMapper fdAccountDTOMapper
    ) {
        this.fdAccountRepo = fdAccountRepo;
        this.customerService = customerService;
        this.fdAccountDTOMapper = fdAccountDTOMapper;
    }

    public void  createFDAccount(Long customerId, double principalAmount, int maturityPeriod) throws Exception {
        Customer customer = customerService.findCustomerByIdInternal(customerId);
        if(principalAmount >= FDAccount.minPrincipalAmount){
            fdAccountRepo.save(
                    new FDAccount(
                            LocalDateTime.now(), customer, principalAmount, maturityPeriod
                    )
            );
        }
        else{
            throw new Exception("Principal amount must be more than minimum balance.");
        }
    }

    public FDAccountDTO findSavingsAccountById(Long id) {
        return fdAccountRepo.findById(id)
                .map(fdAccountDTOMapper)
                .orElseThrow(() -> new FDAccountNotFoundException("FD account by id " + id + " was not found."));
    }
}
