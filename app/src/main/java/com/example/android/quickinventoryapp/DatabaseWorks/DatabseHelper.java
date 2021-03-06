package com.example.android.quickinventoryapp.DatabaseWorks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.CustomerInfo;
import com.example.android.quickinventoryapp.BaseClasses.HistoryInfo;
import com.example.android.quickinventoryapp.BaseClasses.NotificationMessage;
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
    private static final String CUSTOMER_TABLE_USER_NAME = "user_name";


    private static final String PRODUCT_TABLE = "productstable";
    private static final String _ID1 = "_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String QUANTITY ="quantity";
    private static final String UNIT ="unit";
    private static final String PRICE ="price";
    private static final String ALERT = "alert";
    private static final String PRODUCT_TABLE_USER_NAME = "user_name";

    private static final String HISTORY_TABLE = "historyTable";
    private static final String _ID2 = "id";
    private static final String HISTORYTABLE_CUSTOMER_NAME ="name";
    private static final String HISTORYTABLE_PRODUCT_NAME ="productname";
    private static final String HISTORYTABLE_QUANTITY ="quantity";
    private static final String HISTORYTABLE_DATE ="date";
    private static final String HISTORYTABLE_TOTAL_PRICE="totalPrice";
    private static final String HISTORY_TABLE_USER_NAME = "user_name";

    private static final String NOTIFICATION_TABLE = "notificationtable";
    private static final String _ID3 = "id";
    private static final String NOTIFICATION ="notification";
    private static final String NOTIFICATION_TABLE_USER_NAME = "username";



    private String CREATE_SIGN_UP_TABLE ="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAME+" TEXT NOT NULL, "+PASSWORD+" TEXT NOT NULL, "+SHOP_NAME+" TEXT NOT NULL, "+SHOP_OWNER_NAME
            +" TEXT NOT NULL"+");";
    private String CREATE_CUSTOMERS_INFO_TABLE = "CREATE TABLE "+ CUSTOMER_TABLE +" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CUSTOMER_NAME+" TEXT NOT NULL, "+COMPANY_NAME+" TEXT NOT NULL, "+PHONE+" TEXT NOT NULL, "+EMAIL+" TEXT NOT NULL, "
            +ADDRESS1+" TEXT NOT NULL, "+ADDRESS2+" TEXT NOT NULL,"+CITY+" TEXT NOT NULL, "+ZIP+" TEXT NOT NULL, "+CUSTOMER_TABLE_USER_NAME+" TEXT NOT NULL"+");";

    private String CREATE_PRODUCTS_INFO_TABLE ="CREATE TABLE "+PRODUCT_TABLE+" ("+_ID1+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PRODUCT_ID+" TEXT NOT NULL, "+PRODUCT_NAME+" TEXT NOT NULL, "+QUANTITY+" TEXT NOT NULL, "+UNIT+" TEXT NOT NULL, "+PRICE+" TEXT NOT NULL, "+ALERT+" TEXT NOT NULL, "+PRODUCT_TABLE_USER_NAME+" TEXT NOT NULL"+");";

    private  String CREATE_HISTORY_TABLE = "CREATE TABLE "+HISTORY_TABLE+" ("+_ID2+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HISTORYTABLE_CUSTOMER_NAME +" TEXT NOT NULL, "+HISTORYTABLE_PRODUCT_NAME+" TEXT NOT NULL, "+HISTORYTABLE_QUANTITY+" TEXT NOT NULL, "+HISTORYTABLE_DATE+" TEXT NOT NULL, "+HISTORYTABLE_TOTAL_PRICE+" TEXT NOT NULL, "+HISTORY_TABLE_USER_NAME+" TEXT NOT NULL"+");";

    private String CREATE_NOTIFICATION_TABLE ="CREATE TABLE "+NOTIFICATION_TABLE+" ("+_ID3+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NOTIFICATION+" TEXT NOT NULL, "+NOTIFICATION_TABLE_USER_NAME+" TEXT NOT NULL"+");";

    private String DROP_SIGN_UP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private  String DROP_CUTOMERS_INFO_TABLE ="DROP TABLE IF EXISTS "+ CUSTOMER_TABLE;
    private  String DROP_PRODUCT_INFO_TABLE ="DROP TABLE IF EXISTS "+ PRODUCT_TABLE;
    private String DROP_HISTORY_TABLE = "DROP TABLE IF EXISTS "+HISTORY_TABLE;
    private String DROP_NOTIFICATION_TABLE = "DROP TABLE IF EXISTS "+NOTIFICATION_TABLE;

    Context context;


    public DatabseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        Toast.makeText(context,"ONCREATE IS CALLED",Toast.LENGTH_LONG).show();
        try {
            db.execSQL(CREATE_SIGN_UP_TABLE);
            db.execSQL(CREATE_CUSTOMERS_INFO_TABLE);
            db.execSQL(CREATE_PRODUCTS_INFO_TABLE);
            db.execSQL(CREATE_HISTORY_TABLE);
            db.execSQL(CREATE_NOTIFICATION_TABLE);

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
        db.execSQL(DROP_NOTIFICATION_TABLE);
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

    public long insertNotificationMessage(NotificationMessage message,String uName){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTIFICATION,message.getMessgae());
        contentValues.put(NOTIFICATION_TABLE_USER_NAME,uName);

        long id = database.insert(NOTIFICATION_TABLE,null,contentValues);
        return id;
    }

    public long insertHistory(HistoryInfo historyInfo,String userName){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HISTORYTABLE_CUSTOMER_NAME,historyInfo.getCustomerName());
        contentValues.put(HISTORYTABLE_PRODUCT_NAME,historyInfo.getProductName());
        contentValues.put(HISTORYTABLE_DATE,historyInfo.getDate());
        contentValues.put(HISTORYTABLE_QUANTITY,historyInfo.getQuantity());
        contentValues.put(HISTORYTABLE_TOTAL_PRICE,historyInfo.getTotalPrice());
        contentValues.put(HISTORY_TABLE_USER_NAME,userName);

        long id = database.insert(HISTORY_TABLE,null,contentValues);
        return id;
    }

    public long insertCustomerInfo(CustomerInfo customer,String userName){
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
        contentValues.put(CUSTOMER_TABLE_USER_NAME,userName);
        long id = database.insert(CUSTOMER_TABLE,null,contentValues);
        return id;
    }



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

    public List<CustomerInfo> getAllCustomerInfo(String userName){
      List<CustomerInfo> list = new ArrayList<>();
        CustomerInfo customerInfo;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {_ID,CUSTOMER_NAME,COMPANY_NAME,PHONE,EMAIL,ADDRESS1,ADDRESS2,CITY,ZIP,CUSTOMER_TABLE_USER_NAME};
       // Cursor cursor = database.query(CUSTOMER_TABLE,columns,null,null,null,null,null);
        String[] selection = {userName};
        Cursor cursor = database.query(CUSTOMER_TABLE, columns, CUSTOMER_TABLE_USER_NAME + " =?", selection, null, null, null);

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
            int index9 = cursor.getColumnIndex(CUSTOMER_TABLE_USER_NAME);

            customerInfo = new CustomerInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6),cursor.getString(index7),cursor.getString(index8));

            list.add(customerInfo);
        }

        return list;
    }


    public List<HistoryInfo> getAllHsitory(String userName){
        List<HistoryInfo> history = new ArrayList<>();

        String string = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {_ID2,HISTORYTABLE_CUSTOMER_NAME,HISTORYTABLE_PRODUCT_NAME,HISTORYTABLE_DATE,HISTORYTABLE_QUANTITY,HISTORYTABLE_TOTAL_PRICE,HISTORY_TABLE_USER_NAME};
        //Cursor cursor = database.query(HISTORY_TABLE,columns,null,null,null,null,null);
        String[] selection = {userName};
        Cursor cursor = database.query(HISTORY_TABLE, columns, HISTORY_TABLE_USER_NAME + " =?", selection, null, null, null);
        while(cursor.moveToNext()){
            int index0 =cursor.getColumnIndex(_ID);
            int index1 =cursor.getColumnIndex(HISTORYTABLE_CUSTOMER_NAME);
            int index2 = cursor.getColumnIndex(HISTORYTABLE_PRODUCT_NAME);
            int index3= cursor.getColumnIndex(HISTORYTABLE_DATE);
            int index4 = cursor.getColumnIndex(HISTORYTABLE_QUANTITY);
            int index5 = cursor.getColumnIndex(HISTORYTABLE_TOTAL_PRICE);
            int index6 = cursor.getColumnIndex(HISTORY_TABLE_USER_NAME);


           HistoryInfo historyInfo = new HistoryInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5));
           history.add(historyInfo);
        }
        return history;
    }

    public CustomerInfo getCustomer(String customerName,String compnayName,String userName){
        CustomerInfo customerInfo =null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns ={_ID,CUSTOMER_NAME,COMPANY_NAME,PHONE,EMAIL,ADDRESS1,ADDRESS2,CITY,ZIP,CUSTOMER_TABLE_USER_NAME};
        String[] selection = {customerName,compnayName,userName};

        try {

            Cursor cursor = db.query(DatabseHelper.CUSTOMER_TABLE, columns, CUSTOMER_NAME + " =? AND " + COMPANY_NAME + " =? AND "+CUSTOMER_TABLE_USER_NAME +" =?", selection, null, null, null);
           // String[] selection = {userName};
           // Cursor cursor = db.query(DatabseHelper.CUSTOMER_TABLE, columns, CUSTOMER_TABLE_USER_NAME + " =?", selection, null, null, null);

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
                int index9 = cursor.getColumnIndex(CUSTOMER_TABLE_USER_NAME);

                customerInfo = new CustomerInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6),cursor.getString(index7),cursor.getString(index8));
            }

        }catch (Exception e ){
            Toast.makeText(context,"Cann't get data",Toast.LENGTH_LONG).show();
        }
        return customerInfo;
    }

    public int deleteACustomer(String name,String company,String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereClause = {name,company,userName};
        int id= db.delete(CUSTOMER_TABLE,CUSTOMER_NAME+ " =? and "+COMPANY_NAME+" =? and "+CUSTOMER_TABLE_USER_NAME+" =?",whereClause);
        return id;

    }

    public long insertProduct(ProductInfo productInfo,String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME,productInfo.getProductName());
        contentValues.put(PRODUCT_ID,productInfo.getProductID());
        contentValues.put(QUANTITY,productInfo.getQuatity());
        contentValues.put(UNIT,productInfo.getUnit());
        contentValues.put(PRICE,productInfo.getPrice());
        contentValues.put(ALERT,productInfo.getAlertMeWhen());
        contentValues.put(PRODUCT_TABLE_USER_NAME,userName);
        long id = db.insert(PRODUCT_TABLE,null,contentValues);
        return id;
    }

    public List<ProductInfo> getAllProductInfo(String userName){
        List<ProductInfo> products = new ArrayList<>();
        ProductInfo productInfo;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {_ID1,PRODUCT_NAME,PRODUCT_ID,QUANTITY,UNIT,PRICE,ALERT,PRODUCT_TABLE_USER_NAME};
       // Cursor cursor = database.query(PRODUCT_TABLE,columns,null,null,null,null,null);
        String[] selection = {userName};
        Cursor cursor = database.query(PRODUCT_TABLE, columns, PRODUCT_TABLE_USER_NAME + " =?", selection, null, null, null);

        while(cursor.moveToNext()){
            int index0 =cursor.getColumnIndex(_ID1);
            int index1 =cursor.getColumnIndex(PRODUCT_NAME);
            int index2 =cursor.getColumnIndex(PRODUCT_ID);
            int index3 =cursor.getColumnIndex(QUANTITY);
            int index4 =cursor.getColumnIndex(UNIT);
            int index5 =cursor.getColumnIndex(PRICE);
            int index6 =cursor.getColumnIndex(ALERT);
            int index7 = cursor.getColumnIndex(PRODUCT_TABLE_USER_NAME);


            productInfo = new ProductInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6));

            products.add(productInfo);
        }

        return products;
    }

    public List<NotificationMessage> getAllNotificationMessage(String uName){
        List<NotificationMessage> messages = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns ={_ID3,NOTIFICATION,NOTIFICATION_TABLE_USER_NAME};
        String[] selection = {uName};

        Cursor cursor = database.query(NOTIFICATION_TABLE,columns,NOTIFICATION_TABLE_USER_NAME+" =?",selection,null,null,null);
        while (cursor.moveToNext()){
            int index0 = cursor.getColumnIndex(_ID3);
            int index1 = cursor.getColumnIndex(NOTIFICATION);
            int index2 = cursor.getColumnIndex(NOTIFICATION_TABLE_USER_NAME);

            messages.add(new NotificationMessage(cursor.getString(index1),cursor.getString(index0)));
        }
        return messages;
    }

    public int deleteAProduct(String name,String productid,String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereClause = {name,productid,userName};
        int id= db.delete(PRODUCT_TABLE,PRODUCT_NAME+ " =? and "+PRODUCT_ID+" =? and "+PRODUCT_TABLE_USER_NAME+" =?",whereClause);
        return id;

    }

    public int deleteAllNotification(String uName){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] whereClause = {uName};

        int id = database.delete(NOTIFICATION_TABLE,NOTIFICATION_TABLE_USER_NAME+ " =?",whereClause);
        return id;
    }

    public int deleteAllHistory(String uName){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] whereClause = {uName};

        int id = database.delete(HISTORY_TABLE,HISTORY_TABLE_USER_NAME+ " =?",whereClause);
        return id;
    }


    public int deleteNotification(String id,String uName){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereClause = {String.valueOf(id),uName};
        int row = db.delete(NOTIFICATION_TABLE,_ID3+ " =? and "+NOTIFICATION_TABLE_USER_NAME+" =?",whereClause);
        return row;
    }

    public ProductInfo getProduct(String productName,String productId,String userName){
        ProductInfo productInfo =null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns ={_ID1,PRODUCT_NAME,PRODUCT_ID,QUANTITY,UNIT,PRICE,ALERT,PRODUCT_TABLE_USER_NAME};
        String[] selection = {productName,productId,userName};

        try {

            Cursor cursor = db.query(PRODUCT_TABLE, columns, PRODUCT_NAME + " =? AND " + PRODUCT_ID + " =? AND "+PRODUCT_TABLE_USER_NAME+" =?", selection, null, null, null);

            while(cursor.moveToNext()){
                int index0 =cursor.getColumnIndex(_ID1);
                int index1 =cursor.getColumnIndex(PRODUCT_NAME);
                int index2 =cursor.getColumnIndex(PRODUCT_ID);
                int index3 =cursor.getColumnIndex(QUANTITY);
                int index4 =cursor.getColumnIndex(UNIT);
                int index5 =cursor.getColumnIndex(PRICE);
                int index6 =cursor.getColumnIndex(ALERT);
                int index7 = cursor.getColumnIndex(PRODUCT_TABLE_USER_NAME);


                productInfo = new ProductInfo(cursor.getString(index1),cursor.getString(index2),cursor.getString(index3),cursor.getString(index4),cursor.getString(index5),cursor.getString(index6));
            }

        }catch (Exception e ){
            Toast.makeText(context,"Cann't get data",Toast.LENGTH_LONG).show();
        }
        return productInfo;
    }

    public int updateProductQuantity(String name,String newValue,String prevValue,String userName){
        int value = Integer.parseInt(prevValue)-Integer.parseInt(newValue);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUANTITY,value);
        String[] whereArgs = {name,userName};
        db.update(PRODUCT_TABLE,contentValues,PRODUCT_NAME+" =? AND "+PRODUCT_TABLE_USER_NAME+" =?",whereArgs);
        return value;

    }

    public int addProductQuantity(String name,String newValue,String prevValue,String uName){
        int value = Integer.parseInt(prevValue)+Integer.parseInt(newValue);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUANTITY,value);
        String[] whereArgs = {name,uName};
       int r= db.update(PRODUCT_TABLE,contentValues,PRODUCT_NAME+" =? AND "+PRODUCT_TABLE_USER_NAME+" =?",whereArgs);
        return value;
    }

    public int updateProductPrice(String name,String newPrice,String uName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRICE,newPrice);
        String[] whereArgs = {name,uName};
       int r = db.update(PRODUCT_TABLE,contentValues,PRODUCT_NAME+" =? AND "+PRODUCT_TABLE_USER_NAME+" =?",whereArgs);
        return r;
    }




}
