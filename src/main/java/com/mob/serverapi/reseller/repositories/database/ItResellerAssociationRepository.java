package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface ItResellerAssociationRepository extends JpaRepository<tResellerAssociation,Integer> {

    List<tResellerAssociation> findDistinctByParentReseller_ResellerId(int resellerId);


}
