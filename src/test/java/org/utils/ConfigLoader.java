package org.utils;

import org.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch(EnvType.valueOf(env)){
            case STAGE -> properties = PropertyUtils.propertyLoader("src/main/resources/stg_config.properties");
            case PRODUCTION -> properties = PropertyUtils.propertyLoader("src/main/resources/prod_config.properties");
            default -> throw new IllegalStateException("invalid env type" + env);
        }
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop !=null)
            return prop;

        else throw new RuntimeException("property base url is not specified in stg_config.properties file");
    }

    public String getUsername(){
        String prop = properties.getProperty("username");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("property username is not specified in stg_config.properties file");
    }

    public String getPwd(){
        String prop = properties.getProperty("password");
        if(prop !=null){
            return prop;
        }
        else throw new RuntimeException("property password is not specified in stg_config.properties file");
    }
}
