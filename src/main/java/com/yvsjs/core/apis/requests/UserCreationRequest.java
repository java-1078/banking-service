package com.yvsjs.core.apis.requests;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String identificationNumber;

}
