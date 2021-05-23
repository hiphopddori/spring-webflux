package com.ddori.springwebflux;

import com.ddori.springwebflux.client.XmlWebClient;
import com.ddori.springwebflux.vo.xml.XmlResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@NoArgsConstructor
public class WebfluxService {

    public void findServices() {
        XmlWebClient coopMktApi = new XmlWebClient();

        List<Mono<XmlResponse>> monos = new ArrayList();
        IntStream.range(1,10).forEach(idx -> {
            System.out.println("WebFlux Call Start[" + String.valueOf(idx) +"]");
            monos.add(coopMktApi.findServiceByParameter(XmlResponse.class, idx));
            System.out.println("WebFlux Call End[" + String.valueOf(idx) +"]");
        });

        //List<XmlResponse> list = Flux.merge(monos).collectList().block();
        Flux<XmlResponse> responseFlux = Flux.merge(monos);
        responseFlux.subscribe(response -> System.out.println("responseString = " + response));
        /* 병렬호출 후 동기
        Mono.zip(monos, Arrays::asList) // make parallel network calls and collect it to a list
                .flatMapIterable(objects -> objects) // make flux of objects
                .doOnComplete(() -> System.out.println("Done")) // will be printed on completion of the flux created above
                .subscribe(response -> System.out.println("responseString = " + response)); // subscribe and start emitting values from flux
        */
        /*
        final List<Mono<XmlResponse>> responseMonos = IntStream.range(0, 10).mapToObj(
                index -> (Mono)coopMktApi.findServiceCouponInfo(XmlResponse.class)).collect(Collectors.toList()); // create iterable of mono of network calls

        Mono.zip(responseMonos, Arrays::asList) // make parallel network calls and collect it to a list
                .flatMapIterable(objects -> objects) // make flux of objects
                .doOnComplete(() -> System.out.println("Done")) // will be printed on completion of the flux created above
                .subscribe(response -> System.out.println("responseString = " + response)); // subscribe and start emitting values from flux

         */
        /*
        Mono<XmlResponse> subscript = coopMktApi.findServiceCouponInfo(XmlResponse.class);
        subscript.subscribe(
                x -> System.out.println("########## next: " + x),
                err -> err.printStackTrace());
        System.out.println("####완료");
         */
    }

}
