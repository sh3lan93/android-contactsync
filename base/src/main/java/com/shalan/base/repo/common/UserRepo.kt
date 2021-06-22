package com.shalan.base.repo.common

import com.shalan.base.models.IUser
import com.shalan.base.repo.BaseRepo
import com.shalan.base.services.SerializationService
import com.shalan.base.services.SessionService
import com.shalan.base.services.SharedService
import com.shalan.base.utils.SharePrefConstants

/**
 * Created by Mohamed Shalan on 6/23/20.
 */

class UserRepo(
    sharedPrefService: SharedService,
    sessionService: SessionService,
    serializationService: SerializationService
) : BaseRepo(
    sharedPrefService = sharedPrefService,
    sessionService = sessionService,
    serializationService = serializationService,
) {

    fun isUserLoggedIn(): Boolean =
        sessionService.hasValidSession() || sharedPrefService.get(
            SharePrefConstants.PREF_GUEST_MODE,
            false
        )

    fun hasValidSession() = sessionService.hasValidSession()

    fun <T : IUser> getUser(clazz: Class<T>) = sessionService.getUser(clazz)

}
