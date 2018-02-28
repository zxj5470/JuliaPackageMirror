package com.github.zxj5470.util

import okhttp3.*

var client = OkHttpClient()
val JSON = MediaType.parse("application/json; charset=utf-8")

fun get(url: String, headers: MutableMap<String, String>): String {
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

fun post(url: String, json: String, headers: MutableMap<String, String>): String {
	val body = RequestBody.create(JSON, json)
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