package com.mob.serverapi.utils;

import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.base.UserRole;
import com.mob.serverapi.users.database.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.format.DateTimeFormatter;
import java.util.*;

public  class UserUtils {

    public static  User transformUser(tUser user){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        User u = new User();
        u.setUserId(user.getUserId().toString());
        u.setUserName(user.getUserName());
        u.setUserEmail(user.getUserEmail());
        u.setUserStatus(user.getUserStatus().getDescription());
        u.setLanguagePreference(user.getLanguagePreference());
        u.setThemePreference(user.getThemePreference());
        u.setCreationDate(user.getCreationDate().format(formatter));
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

    public static UserRole transformUserRole(tUserRole userRole){

        UserRole u = new UserRole();
        //u.setUserRoleId(UUID.fromString(userRole.getUserRoleId()));
        u.setUserRoleName(userRole.getUserType().getDescription());
        return  u;
    }

    public static List<UserRole> transformUserRoleList(List<tUserRole> userRoles){

        List<UserRole> us= new ArrayList<UserRole>();

        for (tUserRole u: userRoles) {
            UserRole newUserRole = transformUserRole(u);
            us.add(newUserRole);
        }

        return us;
    }

    public static byte[] createPasswordSalt(){

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    public static byte[] hashPassword (byte[] salt, String password )
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return hash;
    }

    public static boolean verifyHash(String insertedPasswd, byte[] passwordSalt, byte[] passwordHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] hashNewPassword = hashPassword(passwordSalt,insertedPasswd);

        return  Arrays.equals(passwordHash,hashNewPassword);
    }

    public static String generateRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return  generatedString;
    }

}
