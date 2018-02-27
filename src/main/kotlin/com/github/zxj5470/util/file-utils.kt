package com.github.zxj5470.util

import java.io.File

fun listHolonomicRepoAddress() =
		Bundle.message("dir.metadata.jl").toFile().listFiles()
				.filter { it.isDirectory }
				.filter { it.list().contains("url") }
				.map {
					it.listFiles { dir, name ->
						name == "url"
					}.first().absolutePath.toFile().readLines().first()
				}

fun String.trimRepoName()=this.substringAfterLast('/').substringBefore(".git")

fun String.toFile() = File(this)