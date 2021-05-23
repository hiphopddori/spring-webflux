package com.ddori.springwebflux;

import com.ddori.springwebflux.client.XmlWebClient;
import com.ddori.springwebflux.vo.xml.XmlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class WebfuxRestController {


    private final WebfluxService webfluxService;

    @GetMapping("/fluxs")
    public void sendFluxex() {
        webfluxService.findServices();
    }

    @GetMapping("/flux")
    public void sendFlux() {
        XmlWebClient xmlWebClient = new XmlWebClient();
        /*
        Mono<XmlResponse> subscript =  coopMktApi.findServiceCouponInfo();
        subscript.subscribe(
                x -> System.out.println("########## next: " + x.toString()),
                err -> err.printStackTrace());
        System.out.println("####완료");
         */
        Mono<String> subscript =  xmlWebClient.findServiceCouponInfo(String.class);
        subscript.subscribe(
                x -> System.out.println("########## next: " + x),
                err -> err.printStackTrace());
        System.out.println("####완료");
    }

    @GetMapping("/response/xml/parameter/{idx}")
    public String getXml(@PathVariable int idx) {

        if (idx == 2 || idx == 6) {
            try {
                Thread.sleep(1000);
            } catch(Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        System.out.println("WebFlux Server[" + String.valueOf(idx) + "] Called");
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<WEBCLIENT_TEST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://ddori.webflux.kr/\">" +
                "  <RESULTCODE>00</RESULTCODE>" +
                "  <PARAMETER>" + String.valueOf(idx) + "</PARAMETER>" +
                "  <RESULTMSG>처리완료</RESULTMSG>" +
                //"  <COMP_NAME>60계치킨</COMP_NAME>" +
                //"  <COUPONNAME>양념치킨</COUPONNAME>" +
                "</WEBCLIENT_TEST>";
        return xml;
    }


    @GetMapping("/response/xml")
    public String getXml() {
        System.out.println("WebFlux Server Called");
        try {
            Thread.sleep(1000);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<COUPONINFO_OUT xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://gsapi.m2i.kr/\">" +
                "  <RESULTCODE>00</RESULTCODE>" +
                "  <RESULTMSG>처리완료</RESULTMSG>" +
                //"  <COMP_NAME>60계치킨</COMP_NAME>" +
                //"  <COUPONNAME>양념치킨</COUPONNAME>" +
                "</COUPONINFO_OUT>";
        return xml;
    }
}
