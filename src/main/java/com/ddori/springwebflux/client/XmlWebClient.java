package com.ddori.springwebflux.client;

import com.ddori.springwebflux.vo.xml.XmlResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

/** reference site
 * https://godekdls.github.io/Reactive%20Spring/webclient/
 * */

public class XmlWebClient {
    /*
    public Mono<XmlResponse> findServiceCouponInfo() {
        Object body = XmlResponse.class;
        //return sendMono(body,"https://gorest.co.in/public-api/users/123/posts.xml");
        return sendMono(body,"localhost:8090/response/xml");
    }
    */
    public Mono findServiceCouponInfo(Object body) {
        //Object body = String.class;
        //return sendMono(body,"https://gorest.co.in/public-api/users/123/posts.xml");
        return sendMono(body,"localhost:8020/response/xml");
    }

    public Mono findServiceByParameter(Object body,int idx) {
        //Object body = String.class;
        //return sendMono(body,"https://gorest.co.in/public-api/users/123/posts.xml");
        return sendMono(body,"localhost:8020/response/xml/parameter/"+ idx);
    }

    private Mono sendMono(Object body, String url) {

        return WebClient.builder()
                .build()
                .get()
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                /*
                .onStatus(HttpStatus::is2xxSuccessful, response -> {
                    //String.format("xmlSample onStatus " + response.toString());
                    System.out.println(String.format("xmlSample onStatus " + response.toString()));
                    return Mono.empty();
                })*/
                .bodyToMono((Class)body)
                .onErrorMap( e -> {
                    return e;
                }).retry(1);
    }
}
