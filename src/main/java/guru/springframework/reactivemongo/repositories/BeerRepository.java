package guru.springframework.reactivemongo.repositories;

import guru.springframework.reactivemongo.domain.Beer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 22/08/2024 - 18:20
 * @since jdk 1.21
 */
public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
}
