package models

data class CallInfo private constructor(
    val callDate: Long = 0,
    val city: City = City(-1, ""),
    val callDuration: Long = 0L,
    val phoneNumber: Long = 0
) {
    companion object {
        private const val ONE_HOUR_IN_MILLIS = 3600000L
        private const val RU_PHONE_FIRST_POSSIBLE = 89901110000
        private const val RU_PHONE_LAST_POSSIBLE = 89999999999

        fun make(city: City): CallInfo = CallInfo(
            randomDate(),
            city,
            randomDuration(),
            randomPhoneNumber()
        )

        private fun randomDuration(): Long = (0..ONE_HOUR_IN_MILLIS).random()
        private fun randomDate(): Long = System.currentTimeMillis() + randomDuration()
        private fun randomPhoneNumber(): Long = (RU_PHONE_FIRST_POSSIBLE..RU_PHONE_LAST_POSSIBLE).random()
    }
}
