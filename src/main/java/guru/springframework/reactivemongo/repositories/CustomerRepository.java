package guru.springframework.reactivemongo.repositories;

import guru.springframework.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 22/08/2024 - 18:21
 * @since jdk 1.21
 */
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
