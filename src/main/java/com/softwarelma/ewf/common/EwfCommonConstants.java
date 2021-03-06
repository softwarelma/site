package com.softwarelma.ewf.common;

public class EwfCommonConstants {

    public static final String APPLICATION_NAME_CMS = "cms";
    public static final String APPLICATION_NAME_BOOKING = "booking";
    // TODO runtime setting from db
    public static String APPLICATION_NAME = APPLICATION_NAME_CMS;

    public static boolean DB_SHOW_SQL = true;
    public static boolean DB_LOG_DB = true;
    public static String DB_URL = "jdbc:mysql://localhost:3306/ewf";
    public static String DB_USERNAME = "ewf_usr";
    public static String DB_PASSWORD = "ncqgà1ePáB";

    public static final String IMAGES_FOLDER = "/home/ellison/Downloads/ewf/";

    /**
     * Not a constant, it could be modified at runtime.
     */
    public static int QUERY_SELECT_LIMIT = 200;

    // public static String MENU_CLASS_NAME =
    // "com.softwarelma.ewf.client.elem.EwfElemCustomMenu";
    public static String MENU_CLASS_NAME = "com.softwarelma.ewf.client.elem.EwfElemCustomMenuResponsive";

    public static String PAGE_HOME = "home";
    public static String PAGE_APPL = "appl";

    public static String SESSION_SELECTED_PAGE = "ewfSelectedPage";

    public static String STYLE_BACK_COLOR_GREEN = "ewfBackColorGreen";
    public static String STYLE_BACK_GREEN = "ewfBackGreen";

}
