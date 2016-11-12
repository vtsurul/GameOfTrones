package com.softdesign.gameofthrones.data.database;

public class GotDbSchema {

    public static final class HousesTable {

        public static final String NAME = "houses";

        public static final class Fields {
            public static final String ID = "id";
            public static final String URL = "url";
            public static final String WORDS = "words";
            public static final String NAME = "name";
            public static final String REGION = "region";
        }
    }

    public static final class CharactersTable {

        public static final String NAME = "characters";

        public static final class Fields {
            public static final String ID = "id";
            public static final String URL = "url";
            public static final String NAME = "name";
            public static final String BORN = "born";
            public static final String DIED = "died";
            public static final String TITLES = "titles";
            public static final String ALIASES = "aliases";
            public static final String FATHERID = "fatherid";
            public static final String MOTHERID = "motherid";
            public static final String HOUSEID = "houseid";
        }
    }
}
