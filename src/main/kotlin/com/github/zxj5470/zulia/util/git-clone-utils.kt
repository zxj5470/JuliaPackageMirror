package com.github.zxj5470.zulia.util

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
import java.io.IOException
import java.net.*
import java.util.*

val dir = Bundle.message("dir.clone.pkg.root")
fun gitClone(uri: String) = try {
	println("cloning: $uri")
	Git.cloneRepository().setURI(uri).setDirectory("${dir}\\${uri.trimRepoName()}".toFile()).setCredentialsProvider(UsernamePasswordCredentialsProvider("zxj5470", "698188zxjwjdtls")).call()
	println("clone $uri done")
} catch (e: JGitInternalException) {
	if (e.message?.contains("already exists") == true)
		println("it has cloned before")
	else {
		System.err.println("error in :$uri")
		e.printStackTrace()
	}
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