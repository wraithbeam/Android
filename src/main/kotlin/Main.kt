import controllers.AtsStation
import models.CallInfo
import models.City
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun main(args: Array<String>) {
    val call1 = CallInfo.make(City(1, "Moscow"))
    val call2 = CallInfo.make(City(1, "Moscow"))
    val call3 = CallInfo.make(City(1, "Moscow"))
    val call4 = CallInfo.make(City(1, "Moscow"))
    val call5 = CallInfo.make(City(1, "Moscow"))
    val call6 = CallInfo.make(City(2, "Kazan"))
    val call7 = CallInfo.make(City(3, "Rostov"))
    val call8 = CallInfo.make(City(4, "Volga"))

    var atsStation = AtsStation()
    atsStation
        .input(call6)
        .input(call7)
        .input(call3)
        .input(call4)
        .input(call5)
        .input(call1)
        .input(call2)
        .input(call8)
    println("Unsorted array:\n$atsStation")

    atsStation = atsStation.sort { first: CallInfo, second: CallInfo ->
        second.city.id - first.city.id
    }
    println("Sorted array:\n$atsStation")

    var allCallsDuration = 0L
    atsStation.filter { call: CallInfo -> call.city.name == "Moscow" }.forEach { callInfo ->
        allCallsDuration += callInfo.callDuration
    }
    println("For Moscow all calls duration is ${allCallsDuration.toDuration(DurationUnit.MILLISECONDS)}")
}
