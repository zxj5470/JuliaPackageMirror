package com.github.zxj5470.action
import com.github.zxj5470.util.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

suspend fun download(){
	setProxy()
	removeEmptyDirs()
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