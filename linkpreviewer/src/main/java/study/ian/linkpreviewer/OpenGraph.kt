package study.ian.linkpreviewer

data class OpenGraph(val openGraphContentMap: HashMap<String, String> = HashMap()) {
    enum class AttributeType(val attrType: String) {
        PROPERTY("property"),
        NAME("name")
    }

    enum class OpenGraphAttribute(val attribute: String) {
        OG_TITLE("og:title"),
        OG_TYPE("og:type"),
        OG_IMAGE("og:image"),
        OG_URL("og:url"),

        OG_VIDEO_URL("og:video:url"),
        OG_VIDEO_SECURE_URL("og:video:secure_url"),
        OG_VIDEO_TYPE("og:video:type"),
        OG_VIDEO_WIDTH("og:video:width"),
        OG_VIDEO_HEIGHT("og:video:height"),
        OG_VIDEO_TAG("og:video:tag"),
        OG_SITE_NAME("og:site_name"),
        OG_IMAGE_WIDTH("og:image:width"),
        OG_IMAGE_HEIGHT("og:image:height"),
        OG_DESCRIPTION("og:description"),

        AL_IOS_APP_STORE_ID("al:ios:app_store_id"),
        AL_IOS_APP_NAME("al:ios:app_name"),
        AL_IOS_URL("al:ios:url"),
        AL_ANDROID_URL("al:android:url"),
        AL_ANDROID_APP_NAME("al:android:app_name"),
        AL_ANDROID_PACKAGE("al:android:package"),
        AL_WEB_URL("al:web:url"),

        FB_APP_ID("fb:app_id"),

        TWITTER_CARD("twitter:card"),
        TWITTER_SITE("twitter:site"),
        TWITTER_URL("twitter:url"),
        TWITTER_TITLE("twitter:title"),
        TWITTER_DESCRIPTION("twitter:description"),
        TWITTER_IMAGE("twitter:image"),
        TWITTER_APP_NAME_IPHONE("twitter:app:name:iphone"),
        TWITTER_APP_ID_IPHONE("twitter:app:id:iphone"),
        TWITTER_APP_NAME_IPAD("twitter:app:name:ipad"),
        TWITTER_APP_ID_IPAD("twitter:app:id:ipad"),
        TWITTER_APP_URL_IPHONE("twitter:app:url:iphone"),
        TWITTER_APP_URL_IPAD("twitter:app:url:ipad"),
        TWITTER_APP_NAME_GOOGLEPLAY("twitter:app:name:googleplay"),
        TWITTER_APP_ID_GOOGLEPLAY("twitter:app:id:googleplay"),
        TWITTER_APP_URL_GOOGLEPLAY("twitter:app:url:googleplay"),
        TWITTER_PLAYER("twitter:player"),
        TWITTER_PLAYER_WIDTH("twitter:player:width"),
        TWITTER_PLAYER_HEIGHT("twitter:player:height")
    }

    fun isValid() = getContent(OpenGraphAttribute.OG_TITLE) != null
            && getContent(OpenGraphAttribute.OG_TYPE) != null
            && getContent(OpenGraphAttribute.OG_IMAGE) != null
            && getContent(OpenGraphAttribute.OG_URL) != null

    fun getContent(openGraphAttribute: OpenGraphAttribute) = openGraphContentMap[openGraphAttribute.attribute]
}