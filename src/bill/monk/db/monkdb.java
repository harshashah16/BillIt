package bill.monk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class monkdb extends SQLiteOpenHelper   {
	
	/* Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_COMMENTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_COMMENT
			+ " text not null);";
*/
	private static final String DATABASE_NAME = "bill.monk.db";
	private static final int DATABASE_VERSION = 1;
	
	public monkdb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table groups ( _id integer primary key autoincrement," +
				" _name text unique not null); ");
		db.execSQL("create table contacts ( _id integer primary key autoincrement," +
				" _name text not null, _email text not null, _groupId integer);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(monkdb.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS groups");
		db.execSQL("DROP TABLE IF EXISTS contacts");
		onCreate(db);		
	}
}
