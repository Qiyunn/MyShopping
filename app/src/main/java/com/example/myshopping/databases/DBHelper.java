package com.example.myshopping.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.app.Config;
import com.example.myshopping.models.Cart;
import com.example.myshopping.models.Product;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "cartdb";
    private static final int DBVERSION = 3;
    private static final String TABLENAME = "carttb";

    private static final String COLUMN_ID = "itemId";
    private static final String COLUMN_IMAGE="itemImage";
    private static final String COLUMN_NAME = "itemName";
    private static final String COLUMN_QUANTITY = "itemQuantity";
    private static final String COLUMN_PRICE = "itemPrice";


    private SQLiteDatabase database;

    public DBHelper(Context context) {

        super(context, DBNAME, null, DBVERSION);
        this.context = context;
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY = "create table if not exists " + TABLENAME + "(" +
                COLUMN_ID + " integer primary key," +
                COLUMN_IMAGE+" varchar,"+
                COLUMN_NAME + " varchar," +
                COLUMN_QUANTITY + " integer," +
                COLUMN_PRICE + " varchar)";

        try {
            db.execSQL(CREATE_QUERY);
        }catch (SQLException e){
            Log.d("db",e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_QUERY = "drop table "+TABLENAME;
        db.execSQL(DROP_QUERY);
        onCreate(db);
    }

    public boolean isProductAlreayInCart(Product product){
        String query="select * from "+TABLENAME+" where "+COLUMN_ID+"=?";
        Cursor cursor=database.rawQuery(query,new String[]{product.getId()});
        int count=cursor.getCount();
        if (count==0){
            return false;

        }else {
            return true;
        }

    }

    public void insertToCart(Product product,int quantity){
        if(!isProductAlreayInCart(product)){
            Log.d("update","just insert");
        ContentValues values=new ContentValues();

        values.put(COLUMN_ID,product.getId());
        values.put(COLUMN_IMAGE,product.getImage());
        values.put(COLUMN_NAME,product.getPname());
        values.put(COLUMN_QUANTITY,quantity);
        values.put(COLUMN_PRICE,product.getPrize());

        database.insert(TABLENAME,null,values);
            Toast.makeText(context, "Added succefully!", Toast.LENGTH_SHORT).show();
        }else {
            Log.d("update","need to updata");
           updateQuantity(product,quantity);
        }

    }

    public void updateQuantity(Product product,int quantity){
        ArrayList<Cart> arrayList=new ArrayList<>();
        arrayList=selectFromCart();
        Cart cart=new Cart();
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getItemId()==Integer.parseInt(product.getId())){
                cart=arrayList.get(i);
                Log.d("update","the initial quantity is:"+cart.getItemQuantity());
            }
        }
        ContentValues values=new ContentValues();
        values.put(COLUMN_QUANTITY,cart.getItemQuantity()+quantity);

        database.update(TABLENAME,values,COLUMN_ID+"=?",new String[]{product.getId()});
        Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
    }

    public void deleteCart(Cart cart){
        database.delete(TABLENAME,COLUMN_ID+"=?",new String[]{String.valueOf(cart.getItemId())});
        Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Cart> selectFromCart(){
        ArrayList<Cart> arrayList=new ArrayList<Cart>();
        String[] columns={COLUMN_ID,COLUMN_IMAGE,COLUMN_NAME,COLUMN_QUANTITY,COLUMN_PRICE};

        Cursor cursor=database.query(TABLENAME,columns,null,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            do{
                Cart cart= new Cart();
                cart.setItemId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                cart.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                cart.setItemName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                cart.setItemQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)));
                cart.setItemPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)));
                arrayList.add(cart);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return arrayList;
    }

    public int getAmount(){
        int amount=0;
        ArrayList<Cart> arrayList=selectFromCart();
        for(int i=0;i<arrayList.size();i++){
            amount=amount+arrayList.get(i).getItemQuantity();
        }
        return amount;
    }

    public int getPrice(){
        int price=0;
        ArrayList<Cart> arrayList=selectFromCart();
        for(int i=0;i<arrayList.size();i++){
            price=price+arrayList.get(i).getItemQuantity()*Integer.parseInt(arrayList.get(i).getItemPrice());
        }
        return price;
    }

    public void addQuantity(Cart cart){
        ContentValues values=new ContentValues();
        values.put(COLUMN_QUANTITY,cart.getItemQuantity()+1);

        database.update(TABLENAME,values,COLUMN_ID+"=?",new String[]{String.valueOf(cart.getItemId())});
    }

    public void minusQuantity(Cart cart){
        ContentValues values=new ContentValues();
        values.put(COLUMN_QUANTITY,cart.getItemQuantity()-1);

        if(cart.getItemQuantity()==1){
            Toast.makeText(context, "Can not be less!", Toast.LENGTH_SHORT).show();
        }else{
            database.update(TABLENAME,values,COLUMN_ID+"=?",new String[]{String.valueOf(cart.getItemId())});
        }
    }

    public void deletTable(){
        String DELECT_Query="delete from "+TABLENAME;
        database.execSQL(DELECT_Query);
    }


}
