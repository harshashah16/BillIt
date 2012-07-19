package bill.monk.sourceCode;

import java.util.ArrayList;
import java.util.regex.Pattern;

import bill.monk.db.Contacts;
import bill.monk.db.Groups;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class EditGroup extends Activity {

	private Button editGroupNameButton;
	private Button addPersonButton;
	private Button removePersonButton;
	private EditText group_name;

	private Button FLUSHITButton;
	private Groups group;
	private Monk87Activity mainFrame;
	private GridView gridView;
	
	ArrayList<Contacts> contactList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_edit);
		classInteraction.setEditGroup(this);
		mainFrame = classInteraction.getMainFrame();
		Intent i =  getIntent();
		group = (Groups) i.getSerializableExtra(mainFrame.MESSAGE);
		contactList = new ArrayList<Contacts>();
		
		this.editGroupNameButton = (Button) findViewById(R.id.editGrpNameButton);
		this.addPersonButton = (Button) findViewById(R.id.addPersonButton);
		this.removePersonButton = (Button) findViewById(R.id.removePersonButton);
		this.FLUSHITButton = (Button) findViewById(R.id.FLUSHITButton);
		this.group_name = (EditText) findViewById(R.id.group_name);
		this.gridView = (GridView) findViewById(R.id.gridViewContact);
		
		this.group_name.setText(group.get_name());
		contactList.addAll(mainFrame.datasource.getContactsById(group.get_id()));
		
		/* setting the adapter for grid view */
		ArrayAdapter<Contacts> adapter = new ArrayAdapter<Contacts>(this, android.R.layout.simple_list_item_1, contactList);
	    gridView.setAdapter(adapter);
	        
	       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	   public void onItemClick(AdapterView<?> parent, View v,
	    			   int position, long id) {
	    		   Toast.makeText(getApplicationContext(),
	    				   ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
	    	   }
	       });
		
		
		editGroupNameButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO: Edit the groupName.
				
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
				if(mainFrame.datasource.editGroupName(group) > 0) {
					group.set_name(groupName);
				}
				/*  Qurery to edit group name.
					Check if we need to update the adapter.
				*/
			}
		});

		addPersonButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO: Add a contact.
			}
		});

		removePersonButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO: Start UI to remove person.
			}
		});

		FLUSHITButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO: Start activity to send email.
				
				finish();
			}
		});
	}	
	public void startAddContact(){
		Intent i = new Intent(this, AddGroup.class);
		startActivity(i);
	}
}