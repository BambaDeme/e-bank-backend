package com.deme.ahmadou.ebank;

import com.deme.ahmadou.ebank.entities.*;
import com.deme.ahmadou.ebank.enums.AccountStatus;
import com.deme.ahmadou.ebank.enums.OperationType;
import com.deme.ahmadou.ebank.repositories.AccountOperationRepository;
import com.deme.ahmadou.ebank.repositories.BankAccountRepository;
import com.deme.ahmadou.ebank.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
		return args -> {

			// create 3 customer
			Stream.of("Cheikh","Ahmadou","Bamba").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name.toLowerCase()+"@gmail.com");
				customerRepository.save(customer);
			});

			// create 2 accounts for every customer
			customerRepository.findAll().forEach(customer -> {
				// creating a current account
				CurrentAccount currentAccount = new CurrentAccount();

				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setOverDraft(9000);
				currentAccount.setCustomer(customer);

				bankAccountRepository.save(currentAccount);

				//creating a saving account
				SavingAccount savingAccount = new SavingAccount();

				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setCustomer(customer);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setInterestRate(5.5);

				bankAccountRepository.save(savingAccount);

			});

			// create 10 operations for every bank account
			bankAccountRepository.findAll().forEach(account -> {
				for(int i=0; i<10; i++){
					AccountOperation accountOperation = new AccountOperation();

					accountOperation.setAccount(account);
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*1200);
					accountOperation.setType(Math.random()>0.5 ? OperationType.DEBIT : OperationType.CREDIT);

					accountOperationRepository.save(accountOperation);
				}
			});
		};
	}

}
