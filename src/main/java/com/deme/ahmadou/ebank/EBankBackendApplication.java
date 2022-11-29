package com.deme.ahmadou.ebank;

import com.deme.ahmadou.ebank.entities.*;
import com.deme.ahmadou.ebank.enums.AccountStatus;
import com.deme.ahmadou.ebank.enums.OperationType;
import com.deme.ahmadou.ebank.exceptions.BalanceNotSufficientException;
import com.deme.ahmadou.ebank.exceptions.BankAccountNotFoundException;
import com.deme.ahmadou.ebank.exceptions.CustomerNotFoundException;
import com.deme.ahmadou.ebank.repositories.AccountOperationRepository;
import com.deme.ahmadou.ebank.repositories.BankAccountRepository;
import com.deme.ahmadou.ebank.repositories.CustomerRepository;
import com.deme.ahmadou.ebank.services.AccountOperationService;
import com.deme.ahmadou.ebank.services.BankAccountService;
import com.deme.ahmadou.ebank.services.CustomerService;
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
	CommandLineRunner start(CustomerService customerService,
							BankAccountService bankAccountService,
							AccountOperationService accountOperationService,
							BankAccountRepository bankAccountRepository){
		return args -> {

			// create 3 customer
			Stream.of("Bamba","Khadija","Pourmera").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name.toLowerCase()+"@gmail.com");
				customerService.saveCustomer(customer);
			});

			// create 2 accounts for every customer
			customerService.listCustomers().forEach(customer -> {
				// creating a current account
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
				} catch (CustomerNotFoundException e) {
					throw new RuntimeException(e);
				}

				//creating a saving account
				try {
					bankAccountService.saveSavingAccount(Math.random()*120000,5.5,customer.getId());
				} catch (CustomerNotFoundException e) {
					throw new RuntimeException(e);
				}

			});

			// create 10 operations for every bank account
			bankAccountRepository.findAll().forEach(account -> {
				for(int i=0; i<10; i++){

					try {
						accountOperationService.credit(account.getId(),10_000+Math.random()*120_000,"Credit");
						accountOperationService.debit(account.getId(),1000+Math.random()*9000,"Debit");

					} catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
						throw new RuntimeException(e);
					}
				}
			});
		};
	}

}
