package com.shardingsphere;
public enum DataSourceType {

    DEFAULT("default", "default"),
    DATASOURCE_0("ds-0", "ds-0"),
    DATASOURCE_1("ds-1", "ds-1"),    ;

    private String name;

    private String identity;

    DataSourceType(String name, String identity) {
        this.name = name;
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public String getIdentity() {
        return identity;
    }

    public static DataSourceType getDataSourceTypeByName(String name) {
        for (DataSourceType dataSourceType : DataSourceType.values()) {
            if (dataSourceType.name.equals(name)) {
                return dataSourceType;
            }
        }
        throw new RuntimeException("db is not exist." + name);
    }
}