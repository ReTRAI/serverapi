package com.mob.serverapi.reseller.repositories.endpoints;


import com.mob.serverapi.reseller.base.*;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface IResellerRepository {

    Reseller getResellerById(UUID resellerId);

    Reseller getResellerByUserId(UUID userId);

    Reseller getResellerByUserDeviceName(String userDeviceName);

    List<Reseller> getResellerFiltered(@Nullable String resellerId, @Nullable String resellerName,
                                       boolean recursive, @Nullable String field, @Nullable String orderField,
                                       int offset, int numberRecords);

    long getCountResellerFiltered(@Nullable String resellerId, @Nullable String resellerName,
                                       boolean recursive);

    List<ResellerBalance> getResellerBalanceMovements(UUID resellerId, @Nullable String startMovementDate,
                                                      @Nullable String endMovementDate, @Nullable String minValue,
                                                      @Nullable String maxValue, @Nullable String dc,
                                                      @Nullable String field, @Nullable String orderField,
                                                      int offset, int numberRecords);

   long getCountResellerBalanceMovements(UUID resellerId, @Nullable String startMovementDate,
                                                      @Nullable String endMovementDate, @Nullable String minValue,
                                                      @Nullable String maxValue, @Nullable String dc);

    Reseller setReseller(UUID userId, UUID actionUserId);

    boolean setResellerBalanceMovement(UUID resellerId, String debitCredit, float movementValue, String movementType,
                                       String movementDetail, UUID actionUserId);

    boolean removeReseller(UUID resellerId, UUID actionUserId);

    boolean setResellerAssociation(UUID parentResellerId, UUID childResellerId, UUID actionUserId);

    boolean removeResellerAssociation(UUID parentResellerId, UUID childResellerId, UUID actionUserId);

    ResellerAssociation getResellerAssociation(UUID parentResellerId, UUID childResellerId);

    Reseller getResellerParentByChildId(UUID childResellerId);

    List<Reseller> getAvailableResellerParent(UUID resellerId,int offset, int numberRecords);

    long getCountAvailableResellerParent(UUID resellerId);

    boolean isHierarchyValid (UUID resellerId, UUID childResellerId);
}
