package com.mob.serverapi.support.repositories.endpoints;


import com.mob.serverapi.support.base.Support;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface ISupportRepository {

    Support getSupportById(UUID supportId);

    Support getSupportByUserId(UUID userId);

    Support setSupport(UUID userId, UUID actionUserId);

    boolean removeSupport(UUID supportId, UUID actionUserId);


    List<Support> getSupportFiltered(@Nullable String supportId, @Nullable String supportName,
                                     boolean recursive, @Nullable String field, @Nullable String orderField,
                                     int offset, int numberRecords);

    long getCountSupportFiltered(@Nullable String supportId, @Nullable String supportName,
                                 boolean recursive);
}
