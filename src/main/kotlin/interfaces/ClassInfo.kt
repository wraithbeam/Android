package interfaces

import controllers.AtsStation
import models.CallInfo

interface ClassInfo {
    fun sort(comparator: Comparator<CallInfo>): AtsStation

    fun filter(func: (CallInfo) -> Boolean): List<CallInfo>
}
