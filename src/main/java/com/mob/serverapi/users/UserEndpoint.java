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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "userLoginRequest")
    @ResponsePayload
    public UserLoginResponse userLogin (@RequestPayload UserLoginRequest request) {

        UserLoginResponse response = new UserLoginResponse();
        response.setUser(userRepository.userLogin(request.getUserEmail(), request.getUserPassword()));

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
                request.getUserPassword(), request.getUserType(), request.getActionUserId()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "unblockUserRequest")
    @ResponsePayload
    public UnblockUserResponse unblockUser (@RequestPayload UnblockUserRequest request) {

        UnblockUserResponse response = new UnblockUserResponse();
        response.setResult(userRepository.unblockUser(request.getUserId(),request.getActionUserId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeUserPwRequest")
    @ResponsePayload
    public ChangeUserPwResponse changeUserPw (@RequestPayload ChangeUserPwRequest request) {

        ChangeUserPwResponse response = new ChangeUserPwResponse();
        response.setResult(userRepository.changeUserPw(request.getUserId(), request.getPassword(),
                request.getActionUserId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inactivateUserRequest")
    @ResponsePayload
    public InactivateUserResponse inactivateUser (@RequestPayload InactivateUserRequest request) {

        InactivateUserResponse response = new InactivateUserResponse();
        response.setResult(userRepository.inactivateUser(request.getUserId(),request.getActionUserId()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeLangPreferenceRequest")
    @ResponsePayload
    public ChangeLangPreferenceResponse changeLangPreference
            (@RequestPayload ChangeLangPreferenceRequest request) {

        ChangeLangPreferenceResponse response = new ChangeLangPreferenceResponse();
        response.setResult(userRepository.changeLangPreference(request.getUserId(),request.getLang(),
                request.getActionUserId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changeThemePreferenceRequest")
    @ResponsePayload
    public ChangeThemePreferenceResponse changeThemePreference
            (@RequestPayload ChangeThemePreferenceRequest request) {

        ChangeThemePreferenceResponse response = new ChangeThemePreferenceResponse();
        response.setResult(userRepository.changeThemePreference(request.getUserId(),request.getTheme(),
                request.getActionUserId()));

        return response;
    }
}
