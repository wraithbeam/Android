package interfaces

import controllers.AtsStation
import models.CallInfo

interface ClassChange {
    fun input(callInfo: CallInfo): AtsStation

    fun display(index: Int)

    fun delete(index: Int): CallInfo
}
