package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupportAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

interface ItSupportAssociationRepository extends JpaRepository<tSupportAssociation, UUID> {

    List<tSupportAssociation> findDistinctByParentSupport_SupportId(UUID supportId);

    tSupportAssociation findByParentSupport_SupportIdAndChildSupport_SupportId(UUID supportId, UUID supportId1);

    long countByChildSupport_SupportId(UUID supportId);


    


}
