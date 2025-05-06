package com.yvsjs.core.apis.service;

import com.yvsjs.core.apis.dto.User;
import com.yvsjs.core.apis.requests.UserCreationRequest;
import com.yvsjs.core.apis.response.UserCreationResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User readUser(String identification);

    List<User> readUsers(Pageable pageable);

    UserCreationResponse createUser(UserCreationRequest userCreationRequest);

}
