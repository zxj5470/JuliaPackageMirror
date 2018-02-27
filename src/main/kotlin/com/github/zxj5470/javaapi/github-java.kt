package com.github.zxj5470.javaapi

import com.github.zxj5470.util.Bundle
import com.github.zxj5470.util.toFile
import com.github.zxj5470.util.trimRepoName
import org.eclipse.jgit.api.Git
import java.io.IOException
import java.net.Proxy
import java.net.ProxySelector
import java.net.SocketAddress
import java.net.URI
import java.util.Arrays
import java.net.InetSocketAddress
import org.eclipse.jgit.api.errors.JGitInternalException

val dir=Bundle.message("dir.clone.pkg.root")
fun gitClone(uri: String) = try {
	Git.cloneRepository().setURI(uri).setDirectory("$dir\\${uri.trimRepoName()}".toFile()).call()
	println(uri)
} catch (e: JGitInternalException) {
	println(e.printStackTrace())
	println("it has cloned before")
}

fun setProxy() {
	val host = Bundle.message("proxy.host")
	val port = Bundle.message("port").toInt()
	ProxySelector.setDefault(object : ProxySelector() {
		val delegate: ProxySelector? = ProxySelector.getDefault()
		override fun select(uri: URI): List<Proxy> {
			if (uri.toString().contains("github") && uri.toString().contains("https")) {
				return Arrays.asList(Proxy(Proxy.Type.HTTP, InetSocketAddress
						.createUnresolved(host, port)))
			}
			if (uri.toString().contains("github") && uri.toString().contains("http")) {
				return Arrays.asList(Proxy(Proxy.Type.HTTP, InetSocketAddress
						.createUnresolved(host, port)))
			}
			return if (delegate == null)
				Arrays.asList(Proxy.NO_PROXY)
			else
				delegate.select(uri)
		}
		override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
			if (uri == null || sa == null || ioe == null) {
				throw IllegalArgumentException(
						"Arguments can't be null.")
			}
		}
	})
}