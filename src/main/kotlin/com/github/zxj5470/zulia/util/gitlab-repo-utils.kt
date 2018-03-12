package com.github.zxj5470.zulia.util

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
import org.intellij.lang.annotations.Language

val username = Bundle.message("git.username")
val password = Bundle.message("git.password")
val pkgRootDir = Bundle.message("dir.clone.pkg.root")

fun createProject(repoName: String) {
	val gitlabHeaders = mapOf(
			"PRIVATE-TOKEN" to Bundle.message("gitlab.token")
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

fun addRemoteUstc(name: String) {
	val repoName = name.replace(".jl", "-jl")
	val repoURL = "https://git.ustclug.org/zxj5470/$repoName.git"
	val file = "$pkgRootDir\\$name\\.git"
	val git = Git(FileRepository(file))
	val cmd = "git remote add ustc $repoURL"
	val process = Runtime.getRuntime().exec(cmd, null, file.toFile())
	println(process.inputStream.reader().readText())
	println(process.errorStream.reader().readText())
	val push = git.push()
	push.remote = "ustc"
	push.isForce = true
	push.setCredentialsProvider(UsernamePasswordCredentialsProvider(username, password))
	push.call()
}

fun addRemoteGitee(name: String, remoteUrl: String = "https://gitee.com/Julialang") {
	val repoURL = "$remoteUrl/$name.git"
	val file = "$pkgRootDir\\$name\\.git"
	val git = Git(FileRepository(file))
	var cmd = "git remote add gitee $repoURL"
	var process = Runtime.getRuntime().exec(cmd, null, file.toFile())
	println(process.inputStream.reader().readText())
	println(process.errorStream.reader().readText())
	cmd = "git remote set-url gitee $repoURL"
	process = Runtime.getRuntime().exec(cmd, null, file.toFile())
	println(process.inputStream.reader().readText())
	println(process.errorStream.reader().readText())
	val push = git.push()
	push.remote = "gitee"
	push.isForce = true
	push.setCredentialsProvider(UsernamePasswordCredentialsProvider(username, password))
	push.call()
}

fun main(args: Array<String>) {
	val name = "AbstractNumbers.jl"
	createProject(name)
	addRemoteUstc(name)
}