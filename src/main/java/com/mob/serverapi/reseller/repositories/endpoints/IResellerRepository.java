package com.mob.serverapi.reseller.repositories.endpoints;


import com.mob.serverapi.reseller.base.Reseller;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface IResellerRepository {

    Reseller getResellerById(UUID resellerId);

    List<Reseller> getResellerFiltered(@Nullable String resellerId, @Nullable String resellerName,
                                       boolean recursive, @Nullable String field, @Nullable String orderField,
                                       int offset, int numberRecords);

    Reseller setReseller(UUID userId, UUID actionUserId);

    boolean removeReseller(UUID resellerId, UUID actionUserId);

    boolean setResellerAssociation(UUID parentResellerId, UUID childResellerId);
}
