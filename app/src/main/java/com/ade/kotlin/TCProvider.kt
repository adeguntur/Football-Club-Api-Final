package com.ade.kotlin

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TCProvider : CCProvider() {
    override val main: CoroutineContext = Unconfined
}