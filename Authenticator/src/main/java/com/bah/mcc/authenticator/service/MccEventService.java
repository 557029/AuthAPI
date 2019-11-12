package com.bah.mcc.authenticator.service;

import com.bah.mcc.authenticator.dataaccess.MccEventDTO;

import java.util.List;

public interface MccEventService {
    List<MccEventDTO> getListOfEvents();
}
