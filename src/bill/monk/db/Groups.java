package bill.monk.db;
import java.io.Serializable;

@SuppressWarnings("serial") 
public class Groups implements Serializable {
	public static final String TABLE_NAME= "Groups";
	public static final String Groups_id= "_id";
	public static final String Groups_name= "_name";
	public static final String[] ALL_COLUMNS = {Groups_id, Groups_name};
	
	private long _id;
	private String _name;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	@Override
	public String toString() {
		return _name;
	}
	
}
