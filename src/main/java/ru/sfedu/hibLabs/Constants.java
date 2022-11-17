package ru.sfedu.hibLabs;

public class Constants {
    public static final String GET_SIZE_DB = "SELECT pg_size_pretty( pg_database_size( 'FatDB' ) );";
    public static final String GET_TABLES_DB = "SELECT table_name FROM information_schema.tables\n" +
            "    WHERE table_schema NOT IN ('information_schema','pg_catalog');";
    public static final String GET_USERS_DB = "select usename from pg_shadow;";
    public static final String GET_COLUMNS_DB = "    select table_name, column_name from information_schema.columns" +
            "    where table_catalog = 'FatDB' and table_schema = 'public' order by table_name desc;";

    public static final String FROM_SQL = "select * from lab5.%s";
    public static final String FROM_HQL = "from %s";
    public static final String DELETE_HQL = "delete from %s";

}


