package com.example.myshopping.app;

import android.telephony.mbms.StreamingServiceInfo;

import com.example.myshopping.activities.ProfileActivity;
import com.example.myshopping.models.Cart;
import com.example.myshopping.models.User;

public class EndPoint {

    private static final String REGISTER="shop_reg.php?";
    private static final String LOGIN="shop_login.php?";
    private static final String EDIT_PROFILE="edit_profile.php?";
    private static final String RESET_PASSWORD="shop_reset_pass.php?&";
    private static final String GET_CATEGORY="cust_category.php?";
    private static final String GET_SUB_CATEGORY="cust_sub_category.php?";
    private static final String GET_PRODUCT="product_details.php?";
    private static final String POST_ORDERS="orders.php?&";
    private static final String ORDER_HISTORY="order_history.php?";
    private static final String TRACK="shipment_track.php?";
    private static final String COUPON="coupon.php?";

    private static final String FNAME="fname";
    private static final String LNAME="lname";
    private static final String ADDRESS="address";
    private static final String EMAIL="email";
    private static final String MOBILE="mobile";
    private static final String PASSWORD="password";
    private static final String NEWPASSWORD="newpassword";

    private static final String API_KEY="api_key";
    private static final String USER_ID="user_id";
    private static final String ID="Id";

    private static final String CID="cid";
    private static final String SID="scid";

    private static final String ITEM_ID="item_id";
    private static final String ITEM_NAME="item_name";
    private static final String ITEM_QUANTITY="item_quantity";
    private static final String FINAL_PRICE="final_price";
    private static final String USER_NAME="user_name";
    private static final String BILLING_ADD="billingadd";
    private static final String DELIVERY_ADD="deliveryadd";
    private static final String ORDER_ID="order_id";
    private static final String COUPONNO="couponno";

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

    public static String getCategory(User user){

        return Config.BASIC_URL_PRODUCT + GET_CATEGORY+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId();
    }

    public static String getSubCategory(String id,User user){
        return Config.BASIC_URL_PRODUCT+GET_SUB_CATEGORY+
                ID+"="+id+"&"+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId();
    }

    public static String getProductList(String cid,String sid,User user){
        return Config.BASIC_URL_PRODUCT+GET_PRODUCT+
                CID+"="+cid+"&"+
                SID+"="+sid+"&"+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId();

    }

    public static String updateProfile(String fname,String lname,String address,String email,String mobile){
        return Config.BASIC_URL+EDIT_PROFILE+
                FNAME+"="+fname+"&"+
                LNAME+"="+lname+"&"+
                ADDRESS+"="+address+"&"+
                EMAIL+"="+email+"&"+
                MOBILE+"="+mobile;
    }

    public static String resetPassword(String mobile,String password,String newpassword){
        return Config.BASIC_URL+RESET_PASSWORD+
                MOBILE+"="+mobile+"&"+
                PASSWORD+"+"+password+"&"+
                NEWPASSWORD+"="+newpassword;

    }

    public static String postOrder(Cart cart,User user,String billadd,String deliveradd){
        return Config.BASIC_URL+POST_ORDERS+
                ITEM_ID+"="+ String.valueOf(cart.getItemId())+"&"+
                ITEM_NAME+"="+cart.getItemName()+"&"+
                ITEM_QUANTITY+"="+cart.getItemQuantity()+"&"+
                FINAL_PRICE+"="+String.valueOf(cart.getItemQuantity()*Integer.parseInt(cart.getItemPrice()))+"&"+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId()+"&"+
                USER_NAME+"="+user.getFirstname()+" "+user.getLastname()+"&"+
                BILLING_ADD+"="+billadd+"&"+
                DELIVERY_ADD+"="+deliveradd+"&"+
                MOBILE+"="+user.getMobile()+"&"+
                EMAIL+"="+user.getEmail();
    }

    public static String getOrderHistory(User user){
        return Config.BASIC_URL+ORDER_HISTORY+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId()+"&"+
                MOBILE+"="+user.getMobile();
    }

    public static String getTrackStatus(User user,String ordrid){
        return Config.BASIC_URL+TRACK+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId()+"&"+
                ORDER_ID+"="+ordrid;
    }

    public static String returnCoupon(User user, String couponNo){
        return Config.BASIC_URL+COUPON+
                API_KEY+"="+user.getAppapikey()+"&"+
                USER_ID+"="+user.getId()+"&"+
                COUPONNO+"="+couponNo;

    }
}
