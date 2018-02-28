package com.github.zxj5470.util

import DeleteUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun listHolonomicRepoAddress() =
		Bundle.message("dir.metadata.jl").toFile().listFiles()
				.filter { it.isDirectory }
				.filter { it.list().contains("url") }
				.map {
					it.listFiles { dir, name ->
						name == "url"
					}.first().absolutePath.toFile().readLines().first()
				}

fun removeEmptyDirs() {
	val rootDir = Bundle.message("dir.clone.pkg.root").toPath()
	Files.list(rootDir)
			.filter {
				Files.list(it).count() == 1L
				// equals 1 means only `.git` namma(KR: left)
			}.forEach {
				println("delete empty dir: $it")
				DeleteUtil.delete(it.toFile())
			}
}

fun String.trimRepoName() = this.substringAfterLast('/').substringBefore(".git")

fun String.toFile() = File(this)
fun String.toPath() = Paths.get(this)