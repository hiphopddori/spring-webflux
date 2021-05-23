package com.ddori.springwebflux.vo.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@ToString
@NoArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="WEBCLIENT_TEST", namespace = "http://ddori.webflux.kr/")
public class XmlResponse {
    @XmlElement(name = "RESULTCODE", namespace = "http://ddori.webflux.kr/")
    String resultCode;
    @XmlElement(name = "RESULTMSG",  namespace = "http://ddori.webflux.kr/")
    String resultMsg;

    @XmlElement(name = "PARAMETER",  namespace = "http://ddori.webflux.kr/")
    String parameter;


    @Override
    public String toString() {
        return String.format("resultCode:%s,resultMsg:%s,parameter:%s", resultCode, resultMsg,parameter);
    }
}
