package com.github.zxj5470.zulia

import com.github.zxj5470.zulia.util.gitClone
import com.github.zxj5470.zulia.util.setProxy
import com.github.zxj5470.zulia.util.FileManager
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {
	setProxy()
	FileManager.removeEmptyDirs()
	val repos = FileManager.listHolonomicRepoAddress()
	println(repos)
	val jobs = List(repos.size) {
		launch(CommonPool) {
			gitClone(repos[it])
			println(it)
		}
	}
	jobs.forEach { it.join() }
}