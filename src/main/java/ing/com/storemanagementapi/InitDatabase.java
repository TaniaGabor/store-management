package ing.com.storemanagementapi;

import ing.com.storemanagementapi.enums.RoleEnum;
import ing.com.storemanagementapi.model.Product;
import ing.com.storemanagementapi.model.Role;
import ing.com.storemanagementapi.model.User;
import ing.com.storemanagementapi.repository.ProductRepository;
import ing.com.storemanagementapi.repository.RoleRepository;
import ing.com.storemanagementapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Slf4j
@Configuration
public class InitDatabase {

    private static final Logger log = LoggerFactory.getLogger(InitDatabase.class);

    @Bean
    CommandLineRunner init(ProductRepository productRepository, RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            log.info("Initializing database ...");
            productRepository.save(Product.builder()
                    .id(1)
                    .price(3.5f)
                    .name("Banane")
                    .quantity(30)
                    .build());
            productRepository.save(Product.builder()
                    .id(2)
                    .price(2.5f)
                    .name("Portocale")
                    .quantity(23)
                    .build());
            productRepository.save(Product.builder()
                    .id(3)
                    .price(1.5f)
                    .name("Lamai")
                    .quantity(12)
                    .build());
            log.info("Saved products in the database");

            Role admin = roleRepository.save(Role.builder()
                    .role(RoleEnum.ROLE_ADMIN)
                    .build());
            Role client = roleRepository.save(Role.builder()
                    .role(RoleEnum.ROLE_CUSTOMER)
                    .build());


            userRepository.save(User.builder().id(1)
                    .name("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .username("admin")
                    .email("admin@yahoo.com").roles(Collections.singleton(admin))
                    .build());

            userRepository.save(User.builder().id(2)
                    .name("client")
                    .password(new BCryptPasswordEncoder().encode("client"))
                    .username("client")
                    .email("client@yahoo.com").roles(Collections.singleton(client))
                    .build());
            log.info("Saved users in the database");
        };
    }
}
