package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserStatus;
import com.mob.serverapi.users.database.tUser_;
import com.mob.serverapi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

        return repository.existsByUserNameIgnoreCase(userName);
    }

    public boolean userExistsUserEmail(String userEmail) {

        return repository.existsByUserEmailIgnoreCase(userEmail);
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

    public List<tUser> getUserFiltered(@Nullable UUID userId, @Nullable String userName,
                                       @Nullable String userStatus, @Nullable String userEmail,
                                       @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate,
                                       @Nullable String field, @Nullable String orderField, int offset, int numberRecords){

        List<tUser> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tUser> query = cb.createQuery(tUser.class);
        Root<tUser> root = query.from(tUser.class);
        Join<tUser, tUserStatus> status = root.join(tUser_.userStatus);

        query = getPredicates(cb,query,root,status,userId,userName,userStatus,userEmail,startCreationDate,endCreationDate);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if(field.equals("userId") || field.equals("userName") ||
                    field.equals("userEmail") || field.equals("creationDate"))
            {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if(field.equals("userStatus")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(status.get("description")));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(status.get("description")));
            }
        }

        query.orderBy(orders);

        List<tUser> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tUser r: result) { finalList.add(r); }

        return finalList;
    }

    public long getCountUserFiltered(@Nullable UUID userId, @Nullable String userName,
                                     @Nullable String userStatus, @Nullable String userEmail,
                                     @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tUser> query = cb.createQuery(tUser.class);
        Root<tUser> root = query.from(tUser.class);
        Join<tUser, tUserStatus> status = root.join(tUser_.userStatus);

        query = getPredicates(cb,query,root,status,userId,userName,userStatus,userEmail,startCreationDate,endCreationDate);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

    private CriteriaQuery<tUser> getPredicates(CriteriaBuilder cb, CriteriaQuery<tUser> query, Root<tUser> root,
                                               Join<tUser, tUserStatus> status,@Nullable UUID userId, @Nullable String userName,
                                               @Nullable String userStatus, @Nullable String userEmail,
                                               @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate ){

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (userId != null)
            predicates.add(cb.equal(root.get("userId"), userId));
        if (userName != null)
            predicates.add(cb.like(cb.lower(root.get("userName")), "%"+userName.toLowerCase()+"%"));
        if (userEmail != null)
            predicates.add(cb.equal(cb.lower(root.get("userEmail")), userEmail.toLowerCase()));
        if (userStatus != null)
            predicates.add(cb.equal(cb.lower(status.<String>get("description")), userStatus.toLowerCase()));
        if (startCreationDate != null && endCreationDate!= null)
            predicates.add(cb.between(root.get("creationDate"), startCreationDate, endCreationDate));
        if (startCreationDate != null && endCreationDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), startCreationDate));
        if (startCreationDate == null && endCreationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), endCreationDate));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;

    }
}
