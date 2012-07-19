package bill.monk.db;

public class Contacts {
	public static final String TABLE_NAME= "Contacts";
	public static final String Contacts_id= "_id";
	public static final String Contacts_name= "_name";
	public static final String Contacts_groupId= "_groupId";
	public static final String Contacts_email= "_email";
	public static final String[] ALL_COLUMNS = {Contacts_id, Contacts_name, Contacts_email, Contacts_groupId};
	
	private long _id;
	private String _name;
	private String _email;
	private long _groupId;
	
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	public long get_groupId() {
		return _groupId;
	}

	public void set_groupId(long _groupId) {
		this._groupId = _groupId;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}
	
	@Override
	public String toString() {
		return _name;
	}
	
}
