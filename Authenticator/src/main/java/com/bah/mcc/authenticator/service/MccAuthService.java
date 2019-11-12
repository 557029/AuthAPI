package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import com.bah.mcc.authenticator.dataaccess.MccEventDTO;

import java.util.List;

public interface MccAuthService {
    MccCustomerDTO getMccCustomerDTO(MccCustomerDTO customer);
    MccCustomerDTO getMccCustomerDTO(String username, String password);
    List<MccEventDTO> getListEvents();
}
