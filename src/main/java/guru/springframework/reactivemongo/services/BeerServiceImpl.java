package guru.springframework.reactivemongo.services;

import guru.springframework.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 22/08/2024 - 18:25
 * @since jdk 1.21
 */
public class BeerServiceImpl implements BeerService {
    @Override
    public Mono<BeerDTO> saveBeer(BeerDTO beerDTO) {
        return null;
    }

    @Override
    public Mono<BeerDTO> getById(String beerId) {

        return null;
    }
}
