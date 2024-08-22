package guru.springframework.reactivemongo.mappers;

import guru.springframework.reactivemongo.domain.Beer;
import guru.springframework.reactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 22/08/2024 - 17:58
 * @since jdk 1.21
 */
@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);

}
