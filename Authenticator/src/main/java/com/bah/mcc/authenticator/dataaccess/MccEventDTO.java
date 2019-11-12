package com.bah.mcc.authenticator.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MccEventDTO {
    private Long eventId;
    private String description;
    private Date eventDate;
    private int ticketsLeft = 0;
    private int ticketsMax = 0;
}
