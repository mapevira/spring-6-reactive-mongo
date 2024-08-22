package guru.springframework.reactivemongo.services;

import guru.springframework.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 22/08/2024 - 18:23
 * @since jdk 1.21
 */
public interface BeerService {

    Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDTO);

    Mono<BeerDTO> getById(String beerId);

}
