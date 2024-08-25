package guru.springframework.reactivemongo.services;

import guru.springframework.reactivemongo.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 25/08/2024 - 10:34
 * @since jdk 1.21
 */
public interface CustomerService {

    Flux<CustomerDTO> listCustomers();

    Mono<CustomerDTO> getCustomerById(String customerId);

    Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO);

    Mono<CustomerDTO> saveNewCustomer(Mono<CustomerDTO> customerDTO);

    Mono<CustomerDTO> updateCustomer(String customerId, CustomerDTO customerDTO);

    Mono<CustomerDTO> patchCustomer(String customerId, CustomerDTO customerDTO);

    Mono<Void> deleteCustomerById(String customerId);

    Mono<CustomerDTO> findFirstByCustomerName(String customerName);
}
