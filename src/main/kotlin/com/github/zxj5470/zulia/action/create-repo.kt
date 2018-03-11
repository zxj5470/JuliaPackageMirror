package com.github.zxj5470.zulia.action

import com.github.zxj5470.zulia.util.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) = runBlocking {
	var repos = FileManager.listHolonomicLocalDirs().map { it.toString().trimRepoName() }
	repos = repos.sub(260, 100)
	val size = repos.size
	repos = repos.map { it.trimRepoNameByPath() }
	var i = 0
	val jobs = List(repos.size) { index ->
		launch(CommonPool) {
			giteeCreateRepos(repos[index])
			println("${repos[index]} (${index + 1}/$size)")
			addRemoteGitee(repos[index])
			println("${repos[index]} (${++i}/$size) finished")
		}
	}
	jobs.forEach { it.join() }
}