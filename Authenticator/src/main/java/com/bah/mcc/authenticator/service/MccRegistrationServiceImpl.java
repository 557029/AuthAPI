package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MccRegistrationServiceImpl implements MccRegistrationService {

    @Value("${databaseservice.url}")
    private String databaseServiceUrl;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MccRegistrationDTO save(MccRegistrationDTO registration) {
        return restTemplate.postForObject(this.databaseServiceUrl + "/registration", registration, MccRegistrationDTO.class);
    }

    @Override
    public List<MccRegistrationDTO> getListOfAllRegistrations() {
        return restTemplate.getForObject(this.databaseServiceUrl + "/registrations", ArrayList.class);
    }

    @Override
    public List<MccRegistrationDTO> getListOfCustomerRegistrations(String username) {
        String url = String.format(this.databaseServiceUrl + "/registrations/%s", username);
        List<MccRegistrationDTO> list = restTemplate.getForObject(url, ArrayList.class);
        log.info(String.format("Getting %d customer registrations", list.size()));
        return list;
    }

    @Override
    public MccRegistrationDTO getRegistration(Long custId, Long eventId) {
        final String url = this.databaseServiceUrl + "/registration/%d/%d";
        final String urlRun = String.format(url, custId, eventId);
        return restTemplate.getForObject(urlRun, MccRegistrationDTO.class);
    }
}
