package guru.springframework.reactivemongo.web.fn;

import guru.springframework.reactivemongo.model.CustomerDTO;
import guru.springframework.reactivemongo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 25/08/2024 - 11:33
 * @since jdk 1.21
 */
@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerService customerService;
    private final Validator validator;

    private void validate(CustomerDTO customerDTO){
        Errors errors = new BeanPropertyBindingResult(customerDTO, "customerDto");
        validator.validate(customerDTO, errors);

        if (errors.hasErrors()){
            throw new ServerWebInputException(errors.toString());
        }
    }

    public Mono<ServerResponse> deleteCustomerById(final ServerRequest request) {
        return customerService.getCustomerById(request.pathVariable("customerId"))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(customerDTO -> customerService.deleteCustomerById(customerDTO.getId()))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> patchCustomerById(ServerRequest request) {
        return request.bodyToMono(CustomerDTO.class)
                .doOnNext(this::validate)
                .flatMap(customerDTO -> customerService.patchCustomer(request.pathVariable("customerId"), customerDTO))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(saveDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateCustomerById(final ServerRequest request) {
        return request.bodyToMono(CustomerDTO.class)
                .doOnNext(this::validate)
                .flatMap(customerDTO -> customerService.updateCustomer(request.pathVariable("customerId"), customerDTO))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(saveDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> createNewCustomer(final ServerRequest request) {
        return request.bodyToMono(CustomerDTO.class)
                .doOnNext(this::validate)
                .flatMap(customerService::saveNewCustomer)
                .flatMap(saveDTO -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromHttpUrl("http://localhost:8080/" + CustomerRouterConfig.CUSTOMER_PATH
                                        + "/" + saveDTO.getId())
                                .build().toUri())
                        .build());
    }

    public Mono<ServerResponse> getCustomerById(final ServerRequest request){
        return ServerResponse.ok()
                .body(customerService.getCustomerById(request.pathVariable("customerId"))
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))),
                        CustomerDTO.class
                );
    }

    public Mono<ServerResponse> listCustomers(final ServerRequest request) {
        return ServerResponse.ok().body(customerService.listCustomers(), CustomerDTO.class);
    }

}
