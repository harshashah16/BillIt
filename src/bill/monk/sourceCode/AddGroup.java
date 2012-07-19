package bill.monk.sourceCode;

import java.util.regex.Pattern;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddGroup extends Activity{
	
	public static final String TAG = "AddGroup";
	private Button saveButton;
	private EditText group_name;
	private Monk87Activity mainFrame;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_add);
        classInteraction.setAddGroup(this);
        classInteraction.getMainFrame();
        mainFrame = classInteraction.getMainFrame();
        
        saveButton = (Button) findViewById(R.id.groupSaveButton);
        group_name = (EditText) findViewById(R.id.group_name);
        
        saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "addGroupButton clicked");
				String groupName =group_name.getText().toString();
				// Duplicate group.
				if (mainFrame.groupList.contains(groupName)) {
					Toast.makeText(getApplicationContext(),
		    				   "Group already exist.",
		    				   Toast.LENGTH_LONG).show();
					return;
				}
				
				// Check for group length.
				if (groupName.length() > 10 ) {
					Toast.makeText(getApplicationContext(),
		    				   "Group name cannot be more than 10 characters.",
		    				   Toast.LENGTH_LONG).show();
					return;
				}
				
				// Check for special characters.
				Pattern p = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
				if (p.matcher(groupName).find()){
					Toast.makeText(getApplicationContext(),
		    				   "Only aphabets and numbers are allowed for group name.",
		    				   Toast.LENGTH_LONG).show();
					return;
				}
				updateMainScreen();
				finish();
			}
		}); 
    }
    
	public void updateMainScreen(){
		mainFrame.addGroupName(group_name.getText().toString());
	}	
}
