package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccRegistrationDTO;

import java.util.List;

public interface MccRegistrationService {
    MccRegistrationDTO save(MccRegistrationDTO registration);
    List<MccRegistrationDTO> getListOfAllRegistrations();
    List<MccRegistrationDTO> getListOfCustomerRegistrations(String username);
    MccRegistrationDTO getRegistration(Long custId, Long eventId);

}
