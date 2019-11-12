package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;

import java.util.List;

public interface MccCustomerService {
    List<MccCustomerDTO> getListCustomers();
    MccCustomerDTO save(MccCustomerDTO customerDTO);
}
