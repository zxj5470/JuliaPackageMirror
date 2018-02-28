package com.github.zxj5470.zulia.action

import com.github.zxj5470.zulia.util.requests
import org.intellij.lang.annotations.Language

fun createProject(repoName: String) {
	val gitlabHeaders = mapOf(
			"PRIVATE-TOKEN" to "v2yNsNtzrdDwjg_p_LDP"
	)
	val gitlabURL = "https://git.ustclug.org/api/v3/projects?name=$repoName"
	@Language("JSON")
	val data = """{
		"lfs_enabled":"true",
		"request_access_enabled":"true",
		"public":"true"
		}"""
	println(requests.post(gitlabURL, data = data, headers = gitlabHeaders))
}

fun main(args: Array<String>) {
}