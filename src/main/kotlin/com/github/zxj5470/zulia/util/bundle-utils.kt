package com.github.zxj5470.zulia.util

import java.util.*

object Bundle {
	fun message(key: String): String =
			ResourceBundle.getBundle("keys").getString(key)
}

val Any.pr
	get() = println(this)