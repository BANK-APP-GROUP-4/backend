package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.dto.FDAccountDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.FDAccount;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.exception.FDAccountNotFoundException;
import com.wellsfargo.bankapp.mapper.FDAccountDTOMapper;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FDAccountService {
    private final FDAccountRepo fdAccountRepo;
    private final SavingsAccountRepo savingsAccountRepo;
    private final CustomerService customerService;
    private final SavingsAccountService savingsAccountService;
    private final FDAccountDTOMapper fdAccountDTOMapper;
    @Autowired
    public FDAccountService(
            FDAccountRepo fdAccountRepo,
            SavingsAccountRepo savingsAccountRepo, CustomerService customerService,
            SavingsAccountService savingAccountService,
            FDAccountDTOMapper fdAccountDTOMapper
    ) {
        this.fdAccountRepo = fdAccountRepo;
        this.savingsAccountRepo = savingsAccountRepo;
        this.customerService = customerService;
        this.savingsAccountService = savingAccountService;
        this.fdAccountDTOMapper = fdAccountDTOMapper;
    }

    public void createFDAccount(Long customerId, double principalAmount, int maturityPeriod) throws Exception {
        Customer customer = customerService.findCustomerByIdInternal(customerId);
        if (principalAmount >= FDAccount.minPrincipalAmount) {
            fdAccountRepo.save(
                    new FDAccount(
                            customer, LocalDate.now(), principalAmount, maturityPeriod, false
                    )
            );
        } else {
            throw new Exception("Principal amount must be more than minimum balance.");
        }
    }

    public FDAccountDTO findSavingsAccountById(Long id) {
        return fdAccountRepo.findById(id)
                .map(fdAccountDTOMapper)
                .orElseThrow(() -> new FDAccountNotFoundException("FD account by id " + id + " was not found."));
    }

    public List<FDAccountDTO> findFDAccountsByCustId(Long cust_id) {
        return fdAccountRepo.findByCustId(cust_id)
                .stream()
                .map(fdAccountDTOMapper)
                .collect(Collectors.toList());
    }

    //@Scheduled(cron="0 0 2 * * ?")
    @Scheduled(fixedDelay = 10)
    public void MaturityOperation() {
        List<FDAccount> accounts = fdAccountRepo.findAll();
        for (FDAccount account : accounts) {
            if (isEligible(account) && account.getAccountStatus() == true) {
                List<SavingsAccount> savingsAccounts = savingsAccountService.findSavingsAccountByCustId(account.getCustomer().getId());
                SavingsAccount savingsAccount = savingsAccounts.get(0);
                savingsAccount = savingsAccountService.updateAccountBalance(savingsAccount, account.getAmountAtMaturity());
                savingsAccountRepo.save(savingsAccount);
            }
        }
    }

    // Helper methods for calculating and updating interest
    private boolean isEligible(FDAccount fdAccount) {
        // Add logic to determine if the account is eligible for interest calculation
        // For example, check if the activation date is before today
        Boolean isEligible = fdAccount.getActivationDate().plusYears(fdAccount.getMaturityPeriod()).isAfter(LocalDate.now());
        return isEligible;
    }

}
