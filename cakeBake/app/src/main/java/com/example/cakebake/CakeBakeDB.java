package com.example.cakebake;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CakeBakeDB extends SQLiteOpenHelper {
    public static final String DBName = "cupCakeDB";
    public static final String tblUser = "cupCakeUser";
    public static final String tblUserInfo = "cupCakeUserInfo";
    public static final String tblcupCakeInfo = "cupCakeInfo";
    public static final String tblcupCakeCAT = "cupCakeCAT";

    //userlogin TBL
    public static final String tblUsername = "UserName";
    public static final String tblUserpassword = "password";
    public static final String tblUserRole = "UserRole";

    //userInfo TBL
    public static final String tblinfoUsername = "UserinfoName";
    public static final String tblinfoUseraddress = "userAddress";
    public static final String tblinfoUserContact = "Contactnumber";

    //Icecream TBL
    public static final String tblcupCakeID = "cupCakeID";
    public static final String tblcupCakeName = "cupCakeName";

    public static final String tblcupCakeFlavour = "cupCakeFlavour";


    public static final String tblcupCakeCategory = "cupCakecategory";
    public static final String tblcupCakeqty = "cupCakeqty";
    public static final String tblcupCakeprice = "cupCakeprice";

    //IcecreamCategory TBL
    public static final String tblcupCakeCATID = "cupCakeCATID";
    public static final String tblcupCakeCATName = "cupCakeCategory";

    private final Context context;


    public CakeBakeDB(@Nullable Context context)
    {
        super(context, DBName, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL("CREATE TABLE " + tblUser + "(" + tblUsername + " TEXT PRIMARY KEY," + tblUserpassword + " TEXT," + tblUserRole + " TEXT)");

        sqlDB.execSQL("CREATE TABLE " + tblUserInfo + "(" + tblUsername + " TEXT PRIMARY KEY," + tblinfoUsername + " TEXT," + tblinfoUseraddress + " TEXT," + tblinfoUserContact + " TEXT)");

        sqlDB.execSQL("CREATE TABLE " + tblcupCakeInfo + "(" + tblcupCakeID + " TEXT, " + tblcupCakeName + " TEXT,"+ tblcupCakeFlavour + " TEXT,"   + tblcupCakeCategory + " TEXT,"+ tblcupCakeqty +" TEXT,"+ tblcupCakeprice +" TEXT, PRIMARY KEY ("+ tblcupCakeID +"," + tblcupCakeName+"))");

        sqlDB.execSQL("CREATE TABLE " + tblcupCakeCAT + "(" + tblcupCakeCATID + " TEXT PRIMARY KEY," + tblcupCakeCATName + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tblUser);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tblUserInfo);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tblcupCakeInfo);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tblcupCakeCAT);
        onCreate(sqLiteDatabase);
    }

    public boolean newUserInfo(String username, String userAddress, String userContact, String userLoginName, String userPassword, String urole)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues tbluserData = new ContentValues();
        ContentValues tbluserInfoData = new ContentValues();

        //userInfo
        tbluserInfoData.put(tblUsername, userLoginName);
        tbluserInfoData.put(tblinfoUsername, username);
        tbluserInfoData.put(tblinfoUseraddress, userAddress);
        tbluserInfoData.put(tblinfoUserContact, userContact);

        long userConfirm = db.insert(tblUserInfo, null, tbluserInfoData);

        if (userConfirm == -1)
        {
            return false;
        }
        else
        {
            //userlogin
            tbluserData.put(tblUsername, userLoginName);
            tbluserData.put(tblUserpassword, userPassword);
            tbluserData.put(tblUserRole, urole);
            long loginConfirm = db.insert(tblUser, null, tbluserData);
            db.close(); // Close the database connection
            return true;
        }
    }

    public Cursor userCheckForLogin(String Lemail, String Lpassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor userFound = db.rawQuery("SELECT * FROM userinfo WHERE userLoginName = ? AND userPassword = ?", new String[]{Lemail, Lpassword});
        userFound.close();
        return userFound;
    }

    public boolean addnewCupCake(String IID,String IName ,String IFlavour,String ICategory,String IQTY,String IPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tblcupCakeAdd= new ContentValues();

        //userInfo
        tblcupCakeAdd.put(tblcupCakeID, IID);
        tblcupCakeAdd.put(tblcupCakeName, IName);
        tblcupCakeAdd.put(tblcupCakeFlavour, IFlavour);

        tblcupCakeAdd.put(tblcupCakeCategory, ICategory);
        tblcupCakeAdd.put(tblcupCakeqty, IQTY);
        tblcupCakeAdd.put(tblcupCakeprice, IPrice);

        long cupCakeConfirm = db.insert(tblcupCakeInfo, null, tblcupCakeAdd);

        if (cupCakeConfirm == -1)
        {
            return false;
        }
        else
        {
            return true;
        }



    }
    public boolean addnewCupCakeCategory(String IID,String ICategory)
    {
       try
       {
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues cakeCATAdd= new ContentValues();

           //userInfo
           cakeCATAdd.put(tblcupCakeCATID, IID);
           cakeCATAdd.put(tblcupCakeCATName, ICategory);
           long cupCakeConfirm = db.insert(tblcupCakeCAT, null, cakeCATAdd);

           if (cupCakeConfirm == -1)
           {
               return false;
           }
           else
           {
               return true;
           }
       }
       catch(Exception ex)
       {
           return false;
       }

    }

    public ArrayList<String> getCatagory()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<String> list=new ArrayList<String>();

        Cursor allIDs=db.rawQuery("SELECT * FROM cupCakeCAT",null);

        if(allIDs.getCount()>0)
        {
            while(allIDs.moveToNext())
            {
                String CAT=allIDs.getString(0);
                list.add(CAT);
            }
        }
        return list;
    }

    public Cursor GetCATName(String CATID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String catgetstr="SELECT cupCakeCategory FROM cupCakeCAT WHERE cupCakeCATID=" + CATID ;

        Toast.makeText(context, catgetstr, Toast.LENGTH_LONG).show();

        Cursor selectedCAT = db.rawQuery(catgetstr, null);

        Toast.makeText(context, selectedCAT.getString(0), Toast.LENGTH_LONG).show();

        return selectedCAT;
    }

    //calling cupCake names from cupCakeinfo TBL
    public ArrayList<String> getcupCakeNames()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<String> cakelist=new ArrayList<String>();

        Cursor allcupCakes=db.rawQuery("SELECT * FROM cupCakeInfo",null);

        if(allcupCakes.getCount()>0)
        {
            while(allcupCakes.moveToNext())
            {
                String IceN=allcupCakes.getString(1);
                cakelist.add(IceN);
            }
        }
        return cakelist;
    }

    //calling Icecream Info

    public Cursor GetcupCakeInfo(String cakename)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String catgetstr="SELECT * FROM cupCakeInfo WHERE cupCakeName='" + cakename.trim() + "'" ;

        Toast.makeText(context, catgetstr, Toast.LENGTH_LONG).show();
        String[] selectionArgs = {cakename};

        return db.rawQuery(catgetstr, selectionArgs);

       // Cursor selectedCAT = db.rawQuery(catgetstr, null);

       // Toast.makeText(context, selectedCAT.getString(0), Toast.LENGTH_LONG).show();

        //return selectedCAT;
    }

    public Integer DelatecupCake(String delcakename)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(tblcupCakeInfo,"cupCakeName=?",new String[]{delcakename});
    }

    public boolean upadatecakeInfo(String cakeFlav,String iceCat,String iceQty,String IcePrice)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contVal=new ContentValues();
        contVal.put(tblcupCakeFlavour,cakeFlav);
        contVal.put(tblcupCakeCategory,iceCat);
        contVal.put(tblcupCakeqty,iceQty);
        contVal.put(tblcupCakeprice,IcePrice);
        db.update(tblcupCakeInfo,contVal,"cupCakeName=?",new String[]{cakeFlav});

        return true;

    }


}
