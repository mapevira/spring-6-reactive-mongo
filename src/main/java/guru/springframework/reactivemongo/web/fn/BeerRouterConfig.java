package guru.springframework.reactivemongo.web.fn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 24/08/2024 - 09:48
 * @since jdk 1.21
 */
@Configuration
@RequiredArgsConstructor
public class BeerRouterConfig {

    public static final String BEER_PATH = "/api/v3/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerHandler handler;

    @Bean
    public RouterFunction<ServerResponse> beerRoutes() {
        return route()
                .GET(BEER_PATH, accept(MediaType.APPLICATION_JSON), handler::listBeers)
                .GET(BEER_PATH_ID, accept(MediaType.APPLICATION_JSON), handler::getBeerById)
                .POST(BEER_PATH, accept(MediaType.APPLICATION_JSON), handler::createNewBeer)
                .PUT(BEER_PATH_ID, accept(MediaType.APPLICATION_JSON), handler::updateBeerById)
                .PATCH(BEER_PATH_ID, accept(MediaType.APPLICATION_JSON), handler::patchBeerById)
                .DELETE(BEER_PATH_ID, accept(MediaType.APPLICATION_JSON), handler::deleteById)
                .build();
    }

}
