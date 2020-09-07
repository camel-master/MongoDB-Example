package me.dave;

import me.dave.account.Account;
import me.dave.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Application {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication();
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

            Account account1 = new Account();
            account1.setEmail("dave@gmail.com");
            account1.setUsername("Dave Kim");

            //1.MongoTemplate를 사용하여 data insert
            mongoTemplate.insert(account1);

            Account account2 = new Account();
            account2.setEmail("camel@gmail.com");
            account2.setUsername("Camel");

            //2. AccountRepository를 사용하여 data insert
            accountRepository.insert(account2);
        };
    }
}
