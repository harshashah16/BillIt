package bill.monk.sourceCode;

import android.widget.Toast;

public class classInteraction {
	
	
	private static Monk87Activity mainFrame;
	private static AddContact addContact;
	private static AddGroup addGroup;
	private static EditGroup editGroup;
	
	public static void setMainFrame (Monk87Activity mainFrame_class){
		mainFrame = mainFrame_class;
	}

	public static Monk87Activity getMainFrame (){
		return mainFrame;
	}
	
	public static void setAddContact (AddContact addContact_class){
		addContact = addContact_class;
	}

	public static AddContact getAddContact (){
		return addContact;
	}
	
	public static void setAddGroup (AddGroup addGroup_class){
		addGroup = addGroup_class;
	}

	public static AddGroup getAddGroup (){
		return addGroup;
	}
	
	public static void setEditGroup (EditGroup editGroup_class){
		editGroup = editGroup_class;
	}

	public static EditGroup getEditGroup (){
		return editGroup;
	}
	
}
