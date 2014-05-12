package Database;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	
	public static final String DATABASE_NAME = "Drinktable.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_NAME = "Drinks";
	
	public static final String NOTIZ_ID = "id";
	public static final String NOTIZ_DRINK = "drink";
	public static final String NOTIZ_TIME = "time";
	


	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_DRINK_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "+
				NOTIZ_ID +" INTEGER PRIMARY KEY, " +
				NOTIZ_DRINK +" TEXT, "+
				NOTIZ_TIME +" TEXT "+ ")";
		
		db.execSQL(CREATE_DRINK_TABLE);
//		onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	
	// in the following code different methods programmed
	
	
	public void writeInDatabase(Variable variable){
		
		// Gets the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();
		
		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		
		values.put(NOTIZ_DRINK, variable.getDrink());				
		values.put(NOTIZ_TIME, variable.getTime());			

		// Insert the new row, returning the primary key value of the new row
		db.insert(TABLE_NAME, null, values);
		
		// close Database
		db.close();
	}
	
	public List<Variable> getAllDrinks(){
		
		List<Variable> drinklist = new ArrayList<Variable>();
		
		// Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
		
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	       	
            	Variable drink = new Variable();
                drink.setDrink(cursor.getString(1));
                drink.setTime(cursor.getString(2));
                // Adding drinks to list
                drinklist.add(drink);
      
            } while (cursor.moveToNext());
        }
		
		return drinklist;
	}
	
}
