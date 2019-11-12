package com.bah.mcc.authenticator.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MccCustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password = "";

}
