package com.iiddd.abnamrorepos.utils

object StringUtils {

    fun getIsPrivateFriendlyString(isPrivate: Boolean): String {
        return if (isPrivate) {
            "Yes"
        } else "No"
    }

    fun capitalize(str: String) =
        str.replaceFirstChar { it.uppercaseChar() }
}