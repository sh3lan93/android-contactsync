package com.shalan.base.repo

import com.shalan.base.services.SerializationService
import com.shalan.base.services.SessionService
import com.shalan.base.services.SharedService

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

open class BaseRepo(
    override val sharedPrefService: SharedService,
    override val sessionService: SessionService,
    override val serializationService: SerializationService
) : IRepo {

}
