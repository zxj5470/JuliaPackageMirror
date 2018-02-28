package com.github.zxj5470.zulia.action

import com.github.zxj5470.zulia.util.*
import org.intellij.lang.annotations.Language



fun main(args: Array<String>) {
	val name=FileManager.listHolonomicLocalDirs().map { it.toString().trimRepoName() }
	println(name.sub(5, 2))
//	createProject(name)
//	addRemote(name)
}