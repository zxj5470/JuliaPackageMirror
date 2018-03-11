package com.github.zxj5470.zulia.util

import okhttp3.*

var client = OkHttpClient()
val JSON = MediaType.parse("application/json; charset=utf-8")

object requests {
	fun get(url: String, headers: Map<String, String> = emptyMap()): String {
		val request = Request.Builder()
				.url(url)
				.headers(Headers.of(headers))
				.build()
		return try {
			client.newCall(request).execute().body()?.string() ?: ""
		} catch (e: Exception) {
			""
		}
	}

	fun post(url: String, data: String, headers: Map<String, String> = emptyMap()): String {
		val body = RequestBody.create(JSON, data)
		val request = Request.Builder()
				.url(url)
				.post(body)
				.headers(Headers.of(headers))
				.build()
		return try {
			client.newCall(request).execute().body()?.string() ?: ""
		} catch (e: Exception) {
			""
		}
	}

	fun delete(url: String): String {
		val request = Request.Builder()
				.url(url)
				.delete()
				.build()
		return try {
			client.newCall(request).execute().body()?.string() ?: ""
		} catch (e: Exception) {
			""
		}
	}
}