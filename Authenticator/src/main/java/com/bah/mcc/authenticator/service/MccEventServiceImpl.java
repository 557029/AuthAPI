package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MccEventServiceImpl implements MccEventService {
    @Value("${databaseservice.url}")
    private String databaseServiceUrl;

    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MccEventDTO> getListOfEvents() {
        final String url = this.databaseServiceUrl + "/listevents";
        List<MccEventDTO> eventDTO =  this.restTemplate.getForObject(url, ArrayList.class);
        log.info("Selected: " + eventDTO.size());
        return eventDTO;
    }
}
