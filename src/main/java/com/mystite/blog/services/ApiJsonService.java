package com.mystite.blog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiJsonService {
    private static final String API_KEY = "80e5555f62a462b047dc608016e493be101142489c338cdb41becf2fc47e8a0a";
    private static final String URL = "https://apiv2.allsportsapi.com/football/?met=Countries&APIkey=" + API_KEY;



    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(URL, String.class);

}
