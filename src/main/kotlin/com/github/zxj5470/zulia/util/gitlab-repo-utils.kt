package com.github.zxj5470.zulia.util

import com.github.zxj5470.zulia.action.createProject
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider

val username = Bundle.message("gitlab.username")
val password = Bundle.message("gitlab.password")
val pkgRootDir = Bundle.message("dir.clone.pkg.root")
fun addRemote(name: String) {
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

fun main(args: Array<String>) {
	val name = "AbstractNumbers.jl"
	createProject(name)
	addRemote(name)
}