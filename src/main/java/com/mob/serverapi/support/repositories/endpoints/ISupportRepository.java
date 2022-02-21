package com.mob.serverapi.support.repositories.endpoints;


import com.mob.serverapi.support.base.Support;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface ISupportRepository {

    Support getSupportById(UUID supportId);

    Support getSupportByUserId(UUID userId);

}
