package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MccCustomerService {
    List<MccCustomerDTO> getListCustomers();
    ResponseEntity<MccCustomerDTO> save(MccCustomerDTO customerDTO, boolean newcustomer);
    MccCustomerDTO getCustomer(String username);
}
