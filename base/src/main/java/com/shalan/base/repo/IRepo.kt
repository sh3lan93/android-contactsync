package com.shalan.base.repo

import com.shalan.base.services.SerializationService
import com.shalan.base.services.SessionService
import com.shalan.base.services.SharedService

/**
 * Created by Mohamed Shalan on 4/18/20.
 */

interface IRepo {

    val sharedPrefService: SharedService

    val sessionService: SessionService

    val serializationService: SerializationService

}
