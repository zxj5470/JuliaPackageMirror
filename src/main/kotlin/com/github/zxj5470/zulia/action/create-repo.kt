package com.github.zxj5470.zulia.action

import com.github.zxj5470.zulia.util.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


fun main(args: Array<String>) = runBlocking {
	var repos = FileManager.listHolonomicLocalDirs().map { it.toString().trimRepoName() }
	val total = repos.size

	val beginIndex = 950
	repos = repos.subList(beginIndex, repos.lastIndex).map { it.trimRepoNameByPath() }
	val size = repos.size
	var i = 0
	val jobs = List(repos.size) { index ->
		launch(CommonPool) {
			giteeCreateRepos(repos[index])
			println("${repos[index]} (${index + 1}/$size) \t\tTOTAL:(${beginIndex + index + 1}/$total)")
			addRemoteGitee(repos[index])
			System.err.println("${repos[index]} (${++i}/$size) finished")
		}
	}
	jobs.forEach { it.join() }
}