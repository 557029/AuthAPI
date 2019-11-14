package com.bah.mcc.authenticator.api;

import com.bah.mcc.authenticator.dataaccess.MccCustomerDTO;
import com.bah.mcc.authenticator.dataaccess.MccEventDTO;
import com.bah.mcc.authenticator.dataaccess.MccRegistrationDTO;
import com.bah.mcc.authenticator.jwt.Token;
import com.bah.mcc.authenticator.jwt.TokenRequestData;
import com.bah.mcc.authenticator.service.MccAuthService;
import com.bah.mcc.authenticator.service.MccCustomerService;
import com.bah.mcc.authenticator.service.MccEventService;
import com.bah.mcc.authenticator.service.MccRegistrationService;
import com.bah.mcc.authenticator.util.JWTHelper;
import com.bah.mcc.authenticator.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
    private MccEventService mccEventService;
    private MccCustomerService mccCustomerService;
    private MccRegistrationService mccRegistrationService;

    private JWTUtil jwtUtil = new JWTHelper();

    @Autowired
    public void setMccAuthService(MccAuthService mccAuthService) {
        this.mccAuthService = mccAuthService;
    }

    @Autowired
    public void setMccEventService(MccEventService mccEventService) {
        this.mccEventService = mccEventService;
    }

    @Autowired
    public void setMccCustomerService(MccCustomerService mccCustomerService) {
        this.mccCustomerService = mccCustomerService;
    }

    @Autowired
    public void setMccRegistrationService(MccRegistrationService mccRegistrationService) {
        this.mccRegistrationService = mccRegistrationService;
    }

    @RequestMapping(value = "/customer"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , method = RequestMethod.GET)
    public MccCustomerDTO consumeUserInfo(@RequestBody MccCustomerDTO customer) {
        return this.mccCustomerService.save(customer, false);
    }

    //todo SignUp customer method was not implemented

    @RequestMapping(value = "/login"
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
    public ResponseEntity<List<MccEventDTO>> getListOfEvents() {
        List<MccEventDTO> list = this.mccEventService.getListOfEvents();
        if(list != null && list.size() > 0) {
            return ResponseEntity.ok(list);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @RequestMapping(value = "/customers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public List<MccCustomerDTO> getListCustomers() {
        return this.mccCustomerService.getListCustomers();
    }

    @RequestMapping(value = "/customer/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public MccCustomerDTO getListCustomer(@PathVariable String username) {
        return this.mccCustomerService.getCustomer(username);
    }


    @RequestMapping(value = "/registrations", method = RequestMethod.GET)
    public List<MccRegistrationDTO> getAllRegistrations() {
        return this.mccRegistrationService.getListOfAllRegistrations();
    }



    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<MccRegistrationDTO> createRegistration(@RequestBody MccRegistrationDTO registrationDTO) {
        this.mccRegistrationService.save(registrationDTO);
        MccRegistrationDTO reg = this.mccRegistrationService.getRegistration(registrationDTO.getCustomerId(), registrationDTO.getEventId());
        if(reg == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(reg);
    }
}
