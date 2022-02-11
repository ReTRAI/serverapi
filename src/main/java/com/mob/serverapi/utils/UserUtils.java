package com.mob.serverapi.utils;

import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.database.*;

import java.util.ArrayList;
import java.util.List;

public  class UserUtils {

    public static  User transformUser(tUser user){

        User u = new User();
        u.setUserId(user.getUserId());
        u.setUserName(user.getUserName());
        u.setUserEmail(user.getUserEmail());
        //u.setUserStatus(user.getUserStatus().toString());
        u.setLanguagePreference(user.getLanguagePreference());
        u.setThemePreference(user.getThemePreference());
        return  u;
    }

    public static List<User> transformUserList(List<tUser> users){

        List<User> us= new ArrayList<User>();

        for (tUser u: users) {
            User newUser = transformUser(u);
            us.add(newUser);
        }

        return us;
    }

}
