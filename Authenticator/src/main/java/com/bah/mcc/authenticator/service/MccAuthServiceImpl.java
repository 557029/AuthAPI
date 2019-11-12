package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import com.bah.mcc.authenticator.dataaccess.MccEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MccAuthServiceImpl implements MccAuthService {
    @Value("${databaseservice.url}")
    private String databaseServiceUrl;

    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MccCustomerDTO getMccCustomerDTO(MccCustomerDTO customer) {
        return getMccCustomerDTO(customer.getUsername(), customer.getPassword());
    }

    @Override
    public MccCustomerDTO getMccCustomerDTO(String username, String password) {
        final String url = this.databaseServiceUrl + "/customer/%s";
        final String urlCust = String.format(url, username);
        MccCustomerDTO customerDTO =  this.restTemplate.getForObject(urlCust, MccCustomerDTO.class);
        log.info(customerDTO.getUsername());
        return customerDTO;
    }
}
