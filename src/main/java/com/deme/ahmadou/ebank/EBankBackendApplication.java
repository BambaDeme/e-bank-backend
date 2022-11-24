package com.deme.ahmadou.ebank;

import com.deme.ahmadou.ebank.entities.Customer;
import com.deme.ahmadou.ebank.repositories.AccountOperationRepository;
import com.deme.ahmadou.ebank.repositories.BankAccountRepository;
import com.deme.ahmadou.ebank.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			Stream.of("Cheikh","Ahmadou","Bamba").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name.toLowerCase()+"@gmail.com");
				customerRepository.save(customer);
			});
		};
	}

}
