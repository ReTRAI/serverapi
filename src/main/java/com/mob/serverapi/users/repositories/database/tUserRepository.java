package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserStatus;
import com.mob.serverapi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tUserRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    ItUserRepository repository;
    @Autowired
    tUserStatusRepository userStatusRepository;

    public tUser saveUser(tUser user) {

        return repository.save(user);
    }

    public tUser findById(UUID userId) {

        return repository.findById(userId).orElse(null);
    }

    public tUser findByEmail(String email) {

        return repository.findByUserEmail(email);
    }

    public tUser findByUserName(String userName) {

        return repository.findByUserName(userName);
    }

    public boolean userExistsUserName(String userName) {

        return repository.existsByUserName(userName);
    }

    public boolean userExistsUserEmail(String userEmail) {

        return repository.existsByUserEmail(userEmail);
    }


    public boolean userExistsByField(tUser user, String field) {

        boolean exists = false;

        ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                .withIgnorePaths("id")
                .withMatcher(field, exact().ignoreCase());

        Example<tUser> userExample = Example.of(user, descriptionMatch);

        exists = repository.exists(userExample);

        return exists;
    }

    public void createDefaultUser() throws NoSuchAlgorithmException, InvalidKeySpecException {

        String userEmail = "noreply@mail.com";
        tUser user = findByEmail(userEmail);

        if (user == null) {
            String langPref = "EN";
            String themePref = "L";
            LocalDateTime creationDate = LocalDateTime.now();
            tUserStatus userStatusVal = userStatusRepository.
                    findUserStatusByDescription(tUserStatus.UserStatusEnum.ACTIVE.name());

            byte[] passwdSalt = UserUtils.createPasswordSalt();
            byte[] passwdHash = UserUtils.hashPassword(passwdSalt, "Teste123");

            tUser userToCreate = new tUser();
            userToCreate.setUserName("Admin");
            userToCreate.setUserEmail(userEmail);
            userToCreate.setPasswordSalt(passwdSalt);
            userToCreate.setPasswordHash(passwdHash);
            userToCreate.setCreationDate(creationDate);
            userToCreate.setThemePreference(themePref);
            userToCreate.setLanguagePreference(langPref);

            userToCreate.setUserStatus(userStatusVal);

            tUser saved = saveUser(userToCreate);
        }
    }
}
