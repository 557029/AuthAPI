package com.bah.mcc.authenticator.api;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import com.bah.mcc.authenticator.dataaccess.MccEventDTO;
import com.bah.mcc.authenticator.jwt.Token;
import com.bah.mcc.authenticator.jwt.TokenRequestData;
import com.bah.mcc.authenticator.service.MccAuthService;
import com.bah.mcc.authenticator.util.JWTHelper;
import com.bah.mcc.authenticator.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:*")
public class AuthRestController {
    private MccAuthService mccAuthService;
    JWTUtil jwtUtil = new JWTHelper();

    @Autowired
    public void setMccAuthService(MccAuthService mccAuthService) {
        this.mccAuthService = mccAuthService;
    }

    @RequestMapping(value = "/customer"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , method = RequestMethod.GET)
    public MccCustomerDTO consumeUserInfo(@RequestBody MccCustomerDTO customer) {
        return this.mccAuthService.getMccCustomerDTO(customer);
    }

    @RequestMapping(value = "customer"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , method = RequestMethod.POST)
    public ResponseEntity<?> loginAndGetToken(@RequestBody TokenRequestData tokenRequestData) {
        final String username = tokenRequestData.getUsername();
        final String password = tokenRequestData.getPassword();

        if (username != null && username.length() > 0
                && password != null && password.length() > 0) {
            final MccCustomerDTO customerDTO = this.mccAuthService.getMccCustomerDTO(username, password);
            if(customerDTO != null) {
                final Token token = jwtUtil.createToken();
                return ResponseEntity.ok(token);
            }
        }
        // bad request
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @RequestMapping(value = "/listevents",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public List<MccEventDTO> getListOfEvents() {
        return this.mccAuthService.getListEvents();
    }
}
