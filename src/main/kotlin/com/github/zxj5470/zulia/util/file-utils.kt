package com.github.zxj5470.zulia.util

import DeleteUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

object FileManager {
	fun listHolonomicRepoAddress() =
			Bundle.message("dir.metadata.jl").toFile().listFiles()
					.filter { it.isDirectory }
					.filter { it.list().contains("url") }
					.map {
						it.listFiles { _, name ->
							name == "url"
						}.first().absolutePath.toFile().readLines().first()
					}

	fun removeEmptyDirs() {
		val rootPath = Bundle.message("dir.clone.pkg.root").toPath()
		Files.list(rootPath)
				.filter {
					Files.list(it).count() == 1L
					// equals 1 means only `.git` namma(KR: left)
				}.forEach {
					println("delete empty dir: $it")
					DeleteUtil.delete(it.toFile())
				}
	}

	fun listHolonomicLocalDirs(): MutableList<Path> = Files.list(Bundle.message("dir.clone.pkg.root").toPath()).collect(Collectors.toList())
}

fun String.trimRepoName() = this.substringAfterLast('/').substringBefore(".git")
fun String.trimRepoNameByPath() = this.substringAfterLast(File.separator)
fun String.toFile() = File(this)
fun String.toPath() = Paths.get(this)!!
fun <E>List<E>.sub(beginIndex:Int, size:Int)=this.subList(beginIndex,beginIndex+ size)