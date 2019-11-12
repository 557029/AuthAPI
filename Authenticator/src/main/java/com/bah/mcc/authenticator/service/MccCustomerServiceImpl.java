package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MccCustomerServiceImpl implements MccCustomerService {
    @Value("${databaseservice.url}")
    private String databaseServiceUrl;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MccCustomerDTO> getListCustomers() {
        final String url = this.databaseServiceUrl + "/customers";
        List<MccCustomerDTO> list =  this.restTemplate.getForObject(url, ArrayList.class);
        return list;
    }

    @Override
    public MccCustomerDTO save(MccCustomerDTO customerDTO) {
        return null;
    }
}
