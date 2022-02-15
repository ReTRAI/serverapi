package com.mob.serverapi.reseller.repositories.endpoints;


import com.mob.serverapi.reseller.base.Reseller;

interface IResellerRepository {

    Reseller getResellerById(int resellerId);

    Reseller setReseller(int userId, int actionUserId);

    boolean removeReseller(int resellerId, int actionUserId);
}
