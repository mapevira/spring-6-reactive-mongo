package guru.springframework.reactivemongo.web.fn;

import guru.springframework.reactivemongo.model.BeerDTO;
import guru.springframework.reactivemongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 24/08/2024 - 09:42
 * @since jdk 1.21
 */
@Component
@RequiredArgsConstructor
public class BeerHandler {

    private final BeerService beerService;

    public Mono<ServerResponse> deleteById(final ServerRequest request) {
        return beerService.deleteBeerById(request.pathVariable("beerId"))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> patchBeerById(final ServerRequest request) {
        return request.bodyToMono(BeerDTO.class)
                .flatMap(beerDTO -> beerService.patchBeer(request.pathVariable("beerId"), beerDTO))
                .flatMap(saveDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateBeerById(final ServerRequest request) {
        return request.bodyToMono(BeerDTO.class)
                .flatMap(beerDTO -> beerService.updateBeer(request.pathVariable("beerId"), beerDTO))
                .flatMap(saveDto -> ServerResponse.noContent().build());

    }

    public Mono<ServerResponse> createNewBeer(final ServerRequest request) {
        return beerService.saveBeer(request.bodyToMono(BeerDTO.class))
                .flatMap(saveDto -> ServerResponse
                    .created(UriComponentsBuilder
                            .fromHttpUrl("http://localhost:8080/" + BeerRouterConfig.BEER_PATH
                            + "/" + saveDto.getId())
                        .build().toUri())
                        .build());
    }

    public Mono<ServerResponse> getBeerById(final ServerRequest request) {
        return ServerResponse.ok()
                .body(beerService.getById(request.pathVariable("beerId")), BeerDTO.class);
    }

    public Mono<ServerResponse> listBeers(final ServerRequest request) {
        return ServerResponse.ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }

}
