package com.once2go.model;

/**
 * Created by once2go on 01.08.16.
 */
public class Config {

    public static final String SERVER = "https://api.trakt.tv/";
    public static final int API_VERSION = 2;

    public static final String CLIENT_ID = "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086";

    public static final int DEFAULT_PAGINATION_LIMIT = 10;
    public static final int DEFAULT_PAGINATION_PAGE = 1;

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";

    public static class SerchBroadcastConfig{
        public static final String INTENT_FILTER = "com.once2go.trakttv.search";
        public static final String INTENT_QUERY_KEY = "INTENT_QUERY_KEY";
    }
}
