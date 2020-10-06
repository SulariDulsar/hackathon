package com.qa.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Configuration {
    private static final Logger LOGGER = Logger.getLogger(Configuration.class);
    private static Properties g_oProperties;

    private static String sSettingsFile;
    private static String sData;

    public static void Load(String file){
        InputStream oIn;
        try {
            sSettingsFile   = file;
            g_oProperties   = new Properties();
            oIn             = new FileInputStream(sSettingsFile);
            g_oProperties.load(oIn);
            oIn.close();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("CONF EXIT LOADING " + sSettingsFile+"~"+e);
            System.exit(0);
        }
        oIn = null;
    }

    public static String getString(String s) {
        try {
            sData = g_oProperties.getProperty(s);
            if (sData == null){
                throw new NullPointerException();
            }
            return sData;
        } catch(Exception e) {
            System.exit(0);
            return null;
        }
    }

    public static String getSafeString(String s) {
        try {
            return g_oProperties.getProperty(s);
        } catch(Exception e) {
            LOGGER.warn("Exception : " ,e);
            return null;
        }
    }

    public static String getSafeStringWithDefaultValue(String s, String defValue) {
        try {
            String temp =  g_oProperties.getProperty(s);
            if(temp==null){
                return defValue;
            }
            return temp;
        } catch(Exception e) {
            LOGGER.warn("Exception : " ,e);
            return defValue;
        }
    }

    public static int getInt(String s){
        try {
            sData = g_oProperties.getProperty(s);
            return Integer.parseInt(sData);
        }catch(Exception e){
            System.exit(0);
            return 0;
        }
    }

    public static int getIntSafe(String s){
        try {
            sData = g_oProperties.getProperty(s);
            return Integer.parseInt(sData);
        }catch(Exception e){
            LOGGER.warn("Exception : " ,e);
            return -1;
        }
    }

    public static int getIntSafeWithDefaultValue(String s, int defValue) {
        try {
            sData = g_oProperties.getProperty(s);
            if (sData == null) {
                LOGGER.warn("Using default configuration for  : " + s + " value : " + defValue);
                return defValue;
            }
            return Integer.parseInt(sData);
        } catch (Exception e) {
            LOGGER.warn("Exception : ", e);
            return defValue;
        }

    }
}
