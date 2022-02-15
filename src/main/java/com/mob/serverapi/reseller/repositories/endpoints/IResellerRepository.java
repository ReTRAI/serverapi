package com.mob.serverapi.reseller.repositories.endpoints;


import com.mob.serverapi.reseller.base.Reseller;

interface IResellerRepository {

    Reseller getResellerById(int resellerId);



}
