package com.mob.serverapi.users;


import com.mob.serverapi.reseller.base.GetCountResellerFilteredRequest;
import com.mob.serverapi.reseller.base.GetCountResellerFilteredResponse;
import com.mob.serverapi.reseller.base.GetResellerFilteredRequest;
import com.mob.serverapi.reseller.base.GetResellerFilteredResponse;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.base.*;
import com.mob.serverapi.users.repositories.endpoints.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/users/base";

    private UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse GetUserById(@RequestPayload GetUserByIdRequest request) {

        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUser(userRepository.getUserById(UUID.fromString(request.getUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UserLoginRequest")
    @ResponsePayload
    public UserLoginResponse UserLogin (@RequestPayload UserLoginRequest request) {

        UserLoginResponse response = new UserLoginResponse();
        response.setUser(userRepository.userLogin(request.getUserEmail(), request.getUserPassword()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserRolesByUserIdRequest")
    @ResponsePayload
    public GetUserRolesByUserIdResponse GetAllUser(@RequestPayload GetUserRolesByUserIdRequest request) {
        GetUserRolesByUserIdResponse response = new GetUserRolesByUserIdResponse();
        response.getUserRole().addAll(userRepository.getUserRolesByUserById(UUID.fromString(request.getUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetUserRequest")
    @ResponsePayload
    public SetUserResponse SetUser (@RequestPayload SetUserRequest request) {

        SetUserResponse response = new SetUserResponse();
        response.setUser(userRepository.setUser(request.getUserName(),request.getUserEmail(),
                request.getUserPassword(), UUID.fromString(request.getActionUserId())));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnblockUserRequest")
    @ResponsePayload
    public UnblockUserResponse UnblockUser (@RequestPayload UnblockUserRequest request) {

        UnblockUserResponse response = new UnblockUserResponse();
        response.setResult(userRepository.unblockUser(UUID.fromString(request.getUserId())
                ,UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangeUserPwRequest")
    @ResponsePayload
    public ChangeUserPwResponse ChangeUserPw (@RequestPayload ChangeUserPwRequest request) {

        ChangeUserPwResponse response = new ChangeUserPwResponse();
        response.setResult(userRepository.changeUserPw(UUID.fromString(request.getUserId()), request.getPassword(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "InactivateUserRequest")
    @ResponsePayload
    public InactivateUserResponse InactivateUser (@RequestPayload InactivateUserRequest request) {

        InactivateUserResponse response = new InactivateUserResponse();
        response.setResult(userRepository.inactivateUser(UUID.fromString(request.getUserId())
                ,UUID.fromString(request.getActionUserId())));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActivateUserRequest")
    @ResponsePayload
    public ActivateUserResponse ActivateUser (@RequestPayload ActivateUserRequest request) {

        ActivateUserResponse response = new ActivateUserResponse();
        response.setResult(userRepository.activateUser(UUID.fromString(request.getUserId())
                ,UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangeLangPreferenceRequest")
    @ResponsePayload
    public ChangeLangPreferenceResponse ChangeLangPreference
            (@RequestPayload ChangeLangPreferenceRequest request) {

        ChangeLangPreferenceResponse response = new ChangeLangPreferenceResponse();
        response.setResult(userRepository.changeLangPreference(UUID.fromString(request.getUserId())
                ,request.getLang(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangeThemePreferenceRequest")
    @ResponsePayload
    public ChangeThemePreferenceResponse ChangeThemePreference
            (@RequestPayload ChangeThemePreferenceRequest request) {

        ChangeThemePreferenceResponse response = new ChangeThemePreferenceResponse();
        response.setResult(userRepository.changeThemePreference(UUID.fromString(request.getUserId()),request.getTheme(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ExistUserNameRequest")
    @ResponsePayload
    public ExistUserNameResponse ExistUserName (@RequestPayload ExistUserNameRequest request) {

        ExistUserNameResponse response = new ExistUserNameResponse();
        response.setResult(userRepository.existUserName(request.getUserName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ExistUserEmailRequest")
    @ResponsePayload
    public ExistUserEmailResponse ExistUserEmail (@RequestPayload ExistUserEmailRequest request) {

        ExistUserEmailResponse response = new ExistUserEmailResponse();
        response.setResult(userRepository.existUserEmail(request.getUserEmail()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserFilteredRequest")
    @ResponsePayload
    public GetUserFilteredResponse GetUserFiltered(@RequestPayload GetUserFilteredRequest request) {

        GetUserFilteredResponse response = new GetUserFilteredResponse();
        response.getUser().addAll(userRepository.getUserFiltered(request.getUserId(),request.getUserName(),
                request.getUserStatus(),request.getUserEmail(),request.getStartCreationDate(),
                request.getEndCreationDate(),request.getField(),request.getOrderField(),
                request.getOffset(),request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountUserFilteredRequest")
    @ResponsePayload
    public GetCountUserFilteredResponse GetCountUserFiltered(@RequestPayload GetCountUserFilteredRequest request) {

        GetCountUserFilteredResponse response = new GetCountUserFilteredResponse();
        response.setResult(userRepository.getCountUserFiltered(request.getUserId(),request.getUserName(),
                request.getUserStatus(),request.getUserEmail(),request.getStartCreationDate(),
                request.getEndCreationDate()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetUserAdminRequest")
    @ResponsePayload
    public SetUserAdminResponse SetUserAdmin (@RequestPayload SetUserAdminRequest request) {

        SetUserAdminResponse response = new SetUserAdminResponse();
        response.setResult(userRepository.setUserAdmin(UUID.fromString(request.getUserId())
                ,UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemoveUserAdminRequest")
    @ResponsePayload
    public RemoveUserAdminResponse RemoveUserAdmin (@RequestPayload RemoveUserAdminRequest request) {

        RemoveUserAdminResponse response = new RemoveUserAdminResponse();
        response.setResult(userRepository.removeUserAdmin(UUID.fromString(request.getUserId())
                ,UUID.fromString(request.getActionUserId())));

        return response;
    }
}
