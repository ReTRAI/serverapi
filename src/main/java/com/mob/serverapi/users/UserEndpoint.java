package com.mob.serverapi.users;


import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.base.*;
import com.mob.serverapi.users.repositories.endpoints.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/users/base";

    private UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) {

        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUser(userRepository.getUserById(request.getUserId()));

        return response;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
//    @ResponsePayload
//    public GetAllUsersResponse getAllUser(@RequestPayload GetAllUsersRequest request) {
//        GetAllUsersResponse response = new GetAllUsersResponse();
//        response.getUsers().addAll(userRepository.getAllUsers());
//
//        return response;
//    }
//
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setUserRequest")
    @ResponsePayload
    public SetUserResponse setUser (@RequestPayload SetUserRequest request) {

        SetUserResponse response = new SetUserResponse();
        response.setUser(userRepository.setUser(request.getUserName(),request.getUserEmail(),
                request.getUserPassword(), request.getUserType()));

        return response;
    }
}
