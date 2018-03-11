package com.github.zxj5470.zulia.util

import org.intellij.lang.annotations.Language

fun giteeCreateRepos(name:String){
	val url = "https://gitee.com/api/v5/orgs/Julialang/repos"
	val token = Bundle.message("gitee.token")
	@Language("json")
	val data = """
		{"access_token":"$token","name":"$name"}""".trimIndent()
	println(requests.post(url = url, data = data))
}
