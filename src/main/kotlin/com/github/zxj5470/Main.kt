package com.github.zxj5470

import com.github.zxj5470.javaapi.gitClone
import com.github.zxj5470.javaapi.setProxy
import com.github.zxj5470.util.listHolonomicRepoAddress
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {
	setProxy()
	val repos = listHolonomicRepoAddress()
	println(repos)
	val jobs = List(repos.size) {
		launch(CommonPool) {
			gitClone(repos[it])
			println(it)
		}
	}
	jobs.forEach { it.join() }
}