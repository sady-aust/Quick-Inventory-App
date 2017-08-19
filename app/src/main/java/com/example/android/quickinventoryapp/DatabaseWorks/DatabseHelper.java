package com.example.android.quickinventoryapp.DatabaseWorks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.CustomerInfo;
import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.BaseClasses.UserInfo;

import java.util.ArrayList;
import java.util.List;


public class DatabseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="signUpDatabase.db";
    private static final String TABLE_NAME ="signUpTable";
    private static final int DATABSE_VERSION = 1;
    private static final String UID = "_id";
    private static final String NAME = "UserName";
    private static final String PASSWORD = "Password";
    private static final String SHOP_NAME = "ShopName";
    private static final String SHOP_OWNER_NAME = "ShopOwnerName";

    private static final String CUSTOMER_TABLE = "customarsTable";
    private static final String _ID = "_id";
    private static final String CUSTOMER_NAME = "CustomerName";
    private static final String COMPANY_NAME = "companyName";
    private static final String PHONE = "Phone";
    private static final String EMAIL = "Email";
    private static final String ADDRESS1 = "Address1";
    private static final String ADDRESS2 = "Address2";
    private static final String CITY = "City";
    private static final String ZIP ="Zip";

    private static final String PRODUCT_TABLE = "productstable";
    private static final String _ID1 = "_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String QUANTITY ="quantity";
    private static final String UNIT ="unit";
    private static final String PRICE ="price";
    private static final String ALERT = "alert";

    private static final String HISTORY_TABLE = "historyTable";
    private static final String _ID2 = "id";
    private static final String HISTORY ="history";



    private String CREATE_SIGN_UP_TABLE ="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAME+" TEXT NOT NULL, "+PASSWORD+" TEXT NOT NULL, "+SHOP_NAME+" TEXT NOT NULL, "+SHOP_OWNER_NAME
            +" TEXT NOT NULL"+");";
    private String CREATE_CUSTOMERS_INFO_TABLE = "CREATE TABLE "+ CUSTOMER_TABLE +" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CUSTOMER_NAME+" TEXT NOT NULL, "+COMPANY_NAME+" TEXT NOT NULL, "+PHONE+" TEXT NOT NULL, "+EMAIL+" TEXT NOT NULL, "
            +ADDRESS1+" TEXT NOT NULL, "+ADDRESS2+" TEXT NOT NULL,"+CITY+" TEXT NOT NULL, "+ZIP+" TEXT NOT NULL"+");";

    private String CREATE_PRODUCTS_INFO_TABLE ="CREATE TABLE "+PRODUCT_TABLE+" ("+_ID1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PRODUCT_ID+" TEXT NOT NULL, "+PRODUCT_NAME+" TEXT NOT NULL, "+QUANTITY+" TEXT NOT NULL, "+UNIT+" TEXT NOT NULL, "+PRICE+" TEXT NOT NULL, "+ALERT+" TEXT NOT NULL"+");";

    private  String CREATE_HISTORY_TABLE = "CREATE TABLE "+HISTORY_TABLE+" ("+_ID2+" INTEGER PRIMARY KEY AUTOINCREMENT, "+HISTORY+" TEXT NOT NULL"+");";

    private String DROP_SIGN_UP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private  String DROP_CUTOMERS_INFO_TABLE ="DROP TABLE IF EXISTS "+ CUSTOMER_TABLE;
    private  String DROP_PRODUCT_INFO_TABLE ="DROP TABLE IF EXISTS "+ PRODUCT_TABLE;
    private String DROP_HISTORY_TABLE = "DROP TABLE IF EXISTS "+HISTORY_TABLE;

    Context context;


    public DatabseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Toast.makeText(context,"ONCREATE IS CALLED",Toast.LENGTH_LONG).show();
        try {
            db.execSQL(CREATE_SIGN_UP_TABLE);
            db.execSQL(CREATE_CUSTOMERS_INFO_TABLE);
            db.execSQL(CREATE_PRODUCTS_INFO_TABLE);
            db.execSQL(CREATE_HISTORY_TABLE);

        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            Log.d("DATABSE ERROR",e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_SIGN_UP_TABLE);
        db.execSQL(DROP_CUTOMERS_INFO_TABLE);
        db.execSQL(DROP_PRODUCT_INFO_TABLE);
        db.execSQL(DROP_HISTORY_TABLE);
        onCreate(db);


        Toast.makeText(context,"ONUPGRADE IS CALLED",Toast.LENGTH_LONG).show();
    }

    public long insertUserinfo(UserInfo user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user.getUserName());
        contentValues.put(PASSWORD,user.getPassword());
        contentValues.put(SHOP_NAME,user.getShopName());
        contentValues.put(SHOP_OWNER_NAME,user.getShopOwneName());

      long id=  database.insert(TABLE_NAME,null,contentValues);
        return id;

    }

    public long insertHistory(String history){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HISTORY,history);
        long id = database.insert(HISTORY_TABLE,null,contentValues);
        return id;
    }

    public long insertCustomerInfo(CustomerInfo customer){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CUSTOMER_NAME,customer.getCutomerName());
        contentValues.put(COMPANY_NAME,customer.getCompanyName());
        contentValues.put(PHONE,customer.getPhone());
        contentValues.put(EMAIL,customer.getEmail());
        contentValues.put(ADDRESS1,customer.getAddressLine1());
        contentValues.put(ADDRESS2,customer.getGetAddressLine2());
        contentValues.put(CITY,customer.getCity());
        contentValues.put(ZIP,customer.getZipCode());
        long id = database.insert(CUSTOMER_TABLE,null,contentValues);
        return id;
    }

  /*  public String getAllUser(){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DatabseHelper.UID, DatabseHelper.NAME, DatabseHelper.PASSWORD, DatabseHelper.SHOP_NAME, DatabseHelper.SHOP_OWNER_NAME};

        Cursor cursor = db.query(DatabseHelper.TABLE_NAME,columns,null,null,null,null,null);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String userName = cursor.getString(1);
            String Password = cursor.getString(2);
            String ShopName = cursor.getString(3);
            String ShopOwnerName = cursor.getString(4);

            buffer.append(id+" "+userName+" "+Password+" "+ShopName+" "+ShopOwnerName+"\n");



        }

        return buffer.toString();

    }*/

    public UserInfo receivingDatafromUserInfoTable(String name,String password) {
        //Select UID,NAME,PASSWORD,SHOP_NAME,SHOP_OWNER_NAME From signupTable where Name = name AND PASSWORD = password
        UserInfo user = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {UID, NAME, PASSWORD,SHOP_NAME, SHOP_OWNER_NAME};
        String[] selection = {name, password};
        try {
            Cursor cursor = db.query(TABLE_NAME, columns, NAME + " =? AND " + PASSWORD + " =?", selection, null, null, null);


            while (cursor.moveToNext()) {
                int index0 =cursor.getColumnIndex(UID);
                int index1 =cursor.getColumnIndex(NAME);
                int index2 =cursor.getColumnIndex(PASSWORD);
                int index3 =cursor.getColumnIndex(SHOP_NAME);
                int index4 =cursor.getColumnIndex(SHOP_OWNER_NAME);


                user = new UserInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4));




            }
        } catch (Exception e) {

            Log.d("ERROR",e.toString());
        }

        //return buffer.toString();
        return user;
    }

    public List<CustomerInfo> getAllCustomerInfo(){
      List<CustomerInfo> list = new ArrayList<>();
        CustomerInfo customerInfo;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {_ID,CUSTOMER_NAME,COMPANY_NAME,PHONE,EMAIL,ADDRESS1,ADDRESS2,CITY,ZIP};
        Cursor cursor = database.query(CUSTOMER_TABLE,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            int index0 =cursor.getColumnIndex(_ID);
            int index1 =cursor.getColumnIndex(CUSTOMER_NAME);
            int index2 =cursor.getColumnIndex(COMPANY_NAME);
            int index3 =cursor.getColumnIndex(PHONE);
            int index4 =cursor.getColumnIndex(EMAIL);
            int index5 =cursor.getColumnIndex(ADDRESS1);
            int index6 =cursor.getColumnIndex(ADDRESS2);
            int index7 =cursor.getColumnIndex(CITY);
            int index8 = cursor.getColumnIndex(ZIP);

            customerInfo = new CustomerInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6),cursor.getString(index7),cursor.getString(index8));

            list.add(customerInfo);
        }

        return list;
    }
    public List<String> getAllHsitory(){
        List<String> history = new ArrayList<>();

        String string = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {_ID2,HISTORY};
        Cursor cursor = database.query(HISTORY_TABLE,columns,null,null,null,null,null);
        while(cursor.moveToNext()){
            int index0 =cursor.getColumnIndex(_ID);
            int index1 =cursor.getColumnIndex(HISTORY);


            string = cursor.getString(index1);
           history.add(string);
        }
        return history;
    }

    public CustomerInfo getCustomer(String customerName,String compnayName){
        CustomerInfo customerInfo =null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns ={_ID,CUSTOMER_NAME,COMPANY_NAME,PHONE,EMAIL,ADDRESS1,ADDRESS2,CITY,ZIP};
        String[] selection = {customerName,compnayName};

        try {

            Cursor cursor = db.query(DatabseHelper.CUSTOMER_TABLE, columns, CUSTOMER_NAME + " =? AND " + COMPANY_NAME + " =?", selection, null, null, null);

            while(cursor.moveToNext()){
                int index0 =cursor.getColumnIndex(_ID);
                int index1 =cursor.getColumnIndex(CUSTOMER_NAME);
                int index2 =cursor.getColumnIndex(COMPANY_NAME);
                int index3 =cursor.getColumnIndex(PHONE);
                int index4 =cursor.getColumnIndex(EMAIL);
                int index5 =cursor.getColumnIndex(ADDRESS1);
                int index6 =cursor.getColumnIndex(ADDRESS2);
                int index7 =cursor.getColumnIndex(CITY);
                int index8 = cursor.getColumnIndex(ZIP);

                customerInfo = new CustomerInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6),cursor.getString(index7),cursor.getString(index8));
            }

        }catch (Exception e ){
            Toast.makeText(context,"Cann't get data",Toast.LENGTH_LONG).show();
        }
        return customerInfo;
    }

    public int deleteACustomer(String name,String company){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereClause = {name,company};
        int id= db.delete(CUSTOMER_TABLE,CUSTOMER_NAME+ " =? and "+COMPANY_NAME+" =?",whereClause);
        return id;

    }

    public long insertProduct(ProductInfo productInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME,productInfo.getProductName());
        contentValues.put(PRODUCT_ID,productInfo.getProductID());
        contentValues.put(QUANTITY,productInfo.getQuatity());
        contentValues.put(UNIT,productInfo.getUnit());
        contentValues.put(PRICE,productInfo.getPrice());
        contentValues.put(ALERT,productInfo.getAlertMeWhen());
        long id = db.insert(PRODUCT_TABLE,null,contentValues);
        return id;
    }

    public List<ProductInfo> getAllProductInfo(){
        List<ProductInfo> products = new ArrayList<>();
        ProductInfo productInfo;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {_ID1,PRODUCT_NAME,PRODUCT_ID,QUANTITY,UNIT,PRICE,ALERT};
        Cursor cursor = database.query(PRODUCT_TABLE,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            int index0 =cursor.getColumnIndex(_ID1);
            int index1 =cursor.getColumnIndex(PRODUCT_NAME);
            int index2 =cursor.getColumnIndex(PRODUCT_ID);
            int index3 =cursor.getColumnIndex(QUANTITY);
            int index4 =cursor.getColumnIndex(UNIT);
            int index5 =cursor.getColumnIndex(PRICE);
            int index6 =cursor.getColumnIndex(ALERT);


            productInfo = new ProductInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6));

            products.add(productInfo);
        }

        return products;
    }

    public int deleteAProduct(String name,String productid){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereClause = {name,productid};
        int id= db.delete(PRODUCT_TABLE,PRODUCT_NAME+ " =? and "+PRODUCT_ID+" =?",whereClause);
        return id;

    }

    public ProductInfo getProduct(String productName,String productId){
        ProductInfo productInfo =null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns ={_ID1,PRODUCT_NAME,PRODUCT_ID,QUANTITY,UNIT,PRICE,ALERT};
        String[] selection = {productName,productId};

        try {

            Cursor cursor = db.query(PRODUCT_TABLE, columns, PRODUCT_NAME + " =? AND " + PRODUCT_ID + " =?", selection, null, null, null);

            while(cursor.moveToNext()){
                int index0 =cursor.getColumnIndex(_ID1);
                int index1 =cursor.getColumnIndex(PRODUCT_NAME);
                int index2 =cursor.getColumnIndex(PRODUCT_ID);
                int index3 =cursor.getColumnIndex(QUANTITY);
                int index4 =cursor.getColumnIndex(UNIT);
                int index5 =cursor.getColumnIndex(PRICE);
                int index6 =cursor.getColumnIndex(ALERT);


                productInfo = new ProductInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6));
            }

        }catch (Exception e ){
            Toast.makeText(context,"Cann't get data",Toast.LENGTH_LONG).show();
        }
        return productInfo;
    }

    public void updateProductQuantity(String name,String newValue,String prevValue){
        int value = Integer.parseInt(prevValue)-Integer.parseInt(newValue);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUANTITY,value);
        String[] whereArgs = {name};
        db.update(PRODUCT_TABLE,contentValues,PRODUCT_NAME+" =? ",whereArgs);

    }



}
