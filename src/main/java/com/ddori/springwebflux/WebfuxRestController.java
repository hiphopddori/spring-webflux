package com.ddori.springwebflux;

import com.ddori.springwebflux.client.XmlWebClient;
import com.ddori.springwebflux.vo.xml.XmlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@RestController
public class WebfuxRestController {


    private final WebfluxService webfluxService;

    @GetMapping("/flux/test")
    public void fluxTest() {
        webfluxService.multiMonoToFlux();
    }

    @GetMapping("/mono/test")
    public void monoTest() {
        XmlWebClient xmlWebClient = new XmlWebClient();

        Mono<String> subscript =  xmlWebClient.findServiceCouponInfo(String.class);
        subscript.subscribe(
                x -> log.debug(String.format("########## next: %s", x)),
                err -> err.printStackTrace());
        log.debug("####완료");
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
        log.debug("server Called : parameter = " + String.valueOf(idx));
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<WEBCLIENT_TEST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://ddori.webflux.kr/\">" +
                "  <RESULTCODE>00</RESULTCODE>" +
                "  <PARAMETER>" + String.valueOf(idx) + "</PARAMETER>" +
                "  <RESULTMSG>처리완료</RESULTMSG>" +
                "</WEBCLIENT_TEST>";
        return xml;
    }


    @GetMapping("/response/xml")
    public String getXml() {
        log.debug("server Called");
        try {
            Thread.sleep(1000);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<WEBCLIENT_TEST xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://ddori.webflux.kr/\">" +
                "  <RESULTCODE>00</RESULTCODE>" +
                "  <RESULTMSG>처리완료</RESULTMSG>" +
                "</COUPONINFO_OUT>";
        return xml;
    }
}
