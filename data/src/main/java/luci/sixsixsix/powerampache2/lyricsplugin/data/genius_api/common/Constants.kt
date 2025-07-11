package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common

import luci.sixsixsix.powerampache2.lyricsplugin.data.BuildConfig

const val BASE_URL = "https://api.genius.com"
const val BEARER_TOKEN = BuildConfig.BEARER_TOKEN
const val TIMEOUT_CONNECTION_S = 15L
const val TIMEOUT_READ_S = 60L
const val TIMEOUT_WRITE_S = 60L
