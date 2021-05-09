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
@XmlRootElement(name="COUPONINFO_OUT", namespace = "http://gsapi.m2i.kr/")
public class XmlResponse {
    @XmlElement(name = "RESULTCODE", namespace = "http://gsapi.m2i.kr/")
    String resultCode;
    @XmlElement(name = "RESULTMSG",  namespace = "http://gsapi.m2i.kr/")
    String resultMsg;


    @Override
    public String toString() {
        return String.format("resultCode:%s,resultMsg:%s", resultCode, resultMsg);
    }
}
