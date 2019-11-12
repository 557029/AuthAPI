package com.bah.mcc.authenticator.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MccRegistrationDTO {
    @NonNull
    private Long customerId;
    @NonNull
    private Long eventId;
    @NonNull
    private Date dateRegistration;
    private int ticketNum;
}
