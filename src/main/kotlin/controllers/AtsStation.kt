package controllers

import interfaces.ClassChange
import interfaces.ClassInfo
import models.CallInfo

class AtsStation(
    private val calls: MutableList<CallInfo> = arrayListOf()
) : ClassInfo, ClassChange {
    override fun input(callInfo: CallInfo) = apply { calls.add(callInfo) }

    override fun display(index: Int) = println(calls[index])

    override fun delete(index: Int) = calls.removeAt(index)

    override fun sort(comparator: Comparator<CallInfo>): AtsStation = apply { calls.sortWith(comparator) }

    override fun filter(func: (CallInfo) -> Boolean): List<CallInfo> = calls.filter(func)

    override fun toString(): String = calls.joinToString("\n")
}
