package com.mob.serverapi.reseller.repositories.endpoints;


import com.mob.serverapi.reseller.base.Reseller;
import org.springframework.lang.Nullable;

import java.util.List;

interface IResellerRepository {

    Reseller getResellerById(int resellerId);

    List<Reseller> getResellerFiltered(@Nullable int resellerId, @Nullable String resellerName,
                                       boolean recursive, @Nullable String field, @Nullable String orderField,
                                       int offset, int numberRecords);

    Reseller setReseller(int userId, int actionUserId);

    boolean removeReseller(int resellerId, int actionUserId);
}
