package com.mob.serverapi.support.repositories.endpoints;

import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.support.base.Support;
import com.mob.serverapi.support.database.tSupport;
import com.mob.serverapi.support.repositories.database.tSupportRepository;
import com.mob.serverapi.utils.SupportUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;


public class SupportRepository implements ISupportRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tSupportRepository supportRepository = new tSupportRepository();


    @Override
    public Support getSupportById(UUID supportId) {

        Support supportToReturn = new Support();

        try {
            tSupport u = supportRepository.findById(supportId);

            if (u != null) {
                supportToReturn = SupportUtils.transformSupport(u);
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_SUPPORT_BY_ID", ex.getMessage()));
        }

        return supportToReturn;
    }

    @Override
    public Support getSupportByUserId(UUID userId) {

        Support supportToReturn = new Support();

        try {
            tSupport u = supportRepository.findByUserId(userId);

            if (u != null) {
                supportToReturn = SupportUtils.transformSupport(u);
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_SUPPORT_BY_USER_ID", ex.getMessage()));
        }

        return supportToReturn;
    }


}

