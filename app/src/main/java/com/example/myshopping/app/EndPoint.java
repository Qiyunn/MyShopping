package com.example.myshopping.app;

public class EndPoint {

    private static final String REGISTER="shop_reg.php?";
    private static final String LOGIN="shop_login.php?";
    private static final String EDIT_PROFILE="edit_profile.php?";
    private static final String RESET_PASSWORD="shop_reset_pass.php?";
    private static final String GET_CATEGORY="cust_category.php?";

    private static final String FNAME="fname";
    private static final String LNAME="lname";
    private static final String ADDRESS="address";
    private static final String EMAIL="email";
    private static final String MOBILE="mobile";
    private static final String PASSWORD="password";

    private static final String API_KEY="api_key";
    private static final String USER_ID="user_id";

    public static String registerUser(String fname, String lname, String address,
                                      String email, String mobile, String password){
        return Config.BASIC_URL+REGISTER+
                FNAME+"="+fname+"&"+
                LNAME+"="+lname+"&"+
                ADDRESS+"="+address+"&"+
                EMAIL+"="+email+"&"+
                MOBILE+"="+mobile+"&"+
                PASSWORD+"="+password;
    }

    public static String loginUser(String mobile, String password){
        return Config.BASIC_URL + LOGIN+
                MOBILE +"="+mobile+"&"+
                PASSWORD + "="+password;
    }

    public static String getCategory(String apiKey,String userId){

        return Config.BASIC_URL_PRODUCT + GET_CATEGORY+
                API_KEY+"="+apiKey+"&"+
                USER_ID+"="+userId;
    }
}
