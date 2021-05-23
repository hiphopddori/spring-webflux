package com.ddori.springwebflux;

import com.ddori.springwebflux.client.XmlWebClient;
import com.ddori.springwebflux.vo.xml.XmlResponse;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@NoArgsConstructor
@Log4j2
public class WebfluxService {

    public void multiMonoToFlux() {

        XmlWebClient xmlWebClient = new XmlWebClient();
        List<Mono<XmlResponse>> monos = new ArrayList();
        IntStream.range(1,10).forEach(idx -> {
            log.debug(String.format("WebFlux Call Start[%d]",idx));
            monos.add(xmlWebClient.findServiceByParameter(XmlResponse.class, idx));
            log.debug(String.format("WebFlux Call End[%d]",idx));
        });

        //List<XmlResponse> list = Flux.merge(monos).collectList().block();
        Flux<XmlResponse> responseFlux = Flux.merge(monos);
        responseFlux.subscribe(response -> log.debug(String.format("response-string = %s",response.toString())));
        /* 병렬호출 후 동기
        Mono.zip(monos, Arrays::asList) // make parallel network calls and collect it to a list
                .flatMapIterable(objects -> objects) // make flux of objects
                .doOnComplete(() -> System.out.println("Done")) // will be printed on completion of the flux created above
                .subscribe(response -> System.out.println("responseString = " + response)); // subscribe and start emitting values from flux
        */
        /*
        final List<Mono<XmlResponse>> responseMonos = IntStream.range(0, 10).mapToObj(
                index -> (Mono)coopMktApi.findServiceCouponInfo(XmlResponse.class)).collect(Collectors.toList()); // create iterable of mono of network calls

        Mono.zip(responseMonos, Arrays::asList)
                .flatMapIterable(objects -> objects) // make flux of objects
                .doOnComplete(() -> System.out.println("Done"))
                .subscribe(response -> System.out.println("responseString = " + response)); // subscribe and start emitting values from flux
         */
    }

}
