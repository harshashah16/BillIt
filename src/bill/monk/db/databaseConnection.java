package bill.monk.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class databaseConnection {

	private monkdb dbHelper;
	private SQLiteDatabase database;
	
	public databaseConnection(Context context) {
		dbHelper = new monkdb(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		System.out.println("Database open.");
	}
	
	public void close() {
		System.out.println("Database closed");
		dbHelper.close();
	}
	
	public Groups createGroup(String groupName) {
		ContentValues values = new ContentValues();
		values.put(Groups.Groups_name, groupName);
		long insertId = database.insert(Groups.TABLE_NAME, null,
				values);
		// Return Null if group name is duplicate.
		if(insertId == 0) {
			return null;
		}
		Cursor cursor = database.query(Groups.TABLE_NAME,
				Groups.ALL_COLUMNS, Groups.Groups_id + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Groups newGroup = cursorToGroups(cursor);
		cursor.close();
		return newGroup;
	}
	
	public int deleteGroups(Groups group) {
		long id = group.get_id();
		System.out.println("Groups deleted with id: " + id);
		return (database.delete(Groups.TABLE_NAME, Groups.Groups_id
				+ " = " + id, null));
		
	}
	
	public int editGroupName(Groups group) {
		long id = group.get_id();
		ContentValues values = new ContentValues();
		values.put(Groups.Groups_name, group.get_name());
		System.out.println("Groups deleted with id: " + id);
		return (database.update(Groups.TABLE_NAME, values, Groups.Groups_id + " = "+ Long.toString(group.get_id()),null));
	}
	
	public List<Groups> getAllGroups() {
		List<Groups> groups = new ArrayList<Groups>();

		Cursor cursor = database.query(Groups.TABLE_NAME,
				Groups.ALL_COLUMNS, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Groups group = cursorToGroups(cursor);
			groups.add(group);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return groups;
	}

	private Groups cursorToGroups(Cursor cursor) {
		Groups group = new Groups();
		group.set_id(cursor.getLong(0));
		group.set_name(cursor.getString(1));
		return group;
	}

	
	
	
	public Contacts createContacts(String contactName, String contactEmail, long groupId) {
		ContentValues values = new ContentValues();
		values.put(Contacts.Contacts_name, contactName);
		values.put(Contacts.Contacts_email, contactEmail);
		values.put(Contacts.Contacts_groupId, groupId);
		
		long insertId = database.insert(Contacts.TABLE_NAME, null,
				values);
		Cursor cursor = database.query(Contacts.TABLE_NAME,
				Contacts.ALL_COLUMNS, Contacts.Contacts_id + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Contacts newGroup = cursorToContacts(cursor);
		cursor.close();
		return newGroup;
	}
	
	public void deleteContacts(Contacts contact) {
		long id = contact.get_id();
		System.out.println("Contacts deleted with id: " + id);
		database.delete(Contacts.TABLE_NAME, Contacts.Contacts_id
				+ " = " + id, null);
	}
	
	public List<Contacts> getContactsById(long groupId) {
		List<Contacts> contacts = new ArrayList<Contacts>();

		Cursor cursor = database.query(Contacts.TABLE_NAME,
				Contacts.ALL_COLUMNS, Contacts.Contacts_groupId+" = ?",
				new String[]{Long.toString(groupId)}, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Contacts contact = cursorToContacts(cursor);
			contacts.add(contact);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return contacts;
	}

	private Contacts cursorToContacts(Cursor cursor) {
		Contacts contact = new Contacts();
		contact.set_id(cursor.getLong(0));
		contact.set_name(cursor.getString(1));
		return contact;
	}
}