package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;

public interface MccAuthService {
    MccCustomerDTO getMccCustomerDTO(MccCustomerDTO customer);
    MccCustomerDTO getMccCustomerDTO(String username, String password);
}
