package com.ets.producer.webservice;

import javax.jws.WebService;
@WebService()
public interface InvokService {
    public String invoke(String param);
}
