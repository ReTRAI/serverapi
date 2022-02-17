package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

interface ItResellerAssociationRepository extends JpaRepository<tResellerAssociation,UUID> {

    List<tResellerAssociation> findDistinctByParentReseller_ResellerId(UUID resellerId);

    boolean existsByParentReseller_ResellerIdAndChildReseller_ResellerId(UUID resellerId, UUID resellerId1);


}
