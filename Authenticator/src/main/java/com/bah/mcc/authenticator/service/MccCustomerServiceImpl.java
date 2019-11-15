package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    public MccCustomerDTO getCustomer(String username) {
        final String url = this.databaseServiceUrl + "/customer/%s";
        final MccCustomerDTO customer = this.restTemplate.getForObject(String.format(url, username), MccCustomerDTO.class);
        log.info("Get Customer: " + customer != null ? customer.getUsername() : "Null");
        return customer;
    }

    @Override
    public ResponseEntity<MccCustomerDTO> save(MccCustomerDTO customerDTO, boolean newcustomer) {
        final String url = this.databaseServiceUrl + "/customer";
        if(newcustomer) {
            // Trying to save a customer
            this.restTemplate.postForObject(url, customerDTO, MccCustomerDTO.class);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MccCustomerDTO> entity = new HttpEntity<>(headers);
            //this.restTemplate.exchange(url + "/" + customerDTO.getUsername(), HttpMethod.PUT, entity, MccCustomerDTO.class, customerDTO);
            this.restTemplate.put(url + "/" + customerDTO.getUsername(), customerDTO, MccCustomerDTO.class);
        }
        // Getting updated customer Info
        final MccCustomerDTO customer = this.getCustomer(customerDTO.getUsername());
        return ResponseEntity.ok(customer);
    }
}
