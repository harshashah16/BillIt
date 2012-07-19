package bill.monk.sourceCode;

import java.util.ArrayList;
import java.util.HashMap;

import bill.monk.db.Groups;
import bill.monk.db.databaseConnection;
import bill.monk.db.monkdb;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class Monk87Activity extends Activity {
    /** Called when the activity is first created. */
	
	public static final String TAG = "MainActivity";
	public static final String MESSAGE = "bill.monk.sourceCode.Monk87Activity";
	private Button addGroupButton;
	public databaseConnection datasource;
	
	private GridView gridView;
	
	ArrayList<Groups> groupList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        classInteraction.setMainFrame(this);
        datasource = new databaseConnection(this);
        datasource.open();
        
        this.addGroupButton = (Button) findViewById(R.id.add_group);
        
        this.gridView = (GridView) findViewById(R.id.gridViewGrp);
        
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "addGroupButton clicked");
                launchAddGroup();
            }
        });
                
       groupList = new ArrayList<Groups>();
       groupList.addAll(datasource.getAllGroups());
       ArrayAdapter<Groups> adapter = new ArrayAdapter<Groups>(this, android.R.layout.simple_list_item_1, groupList);
       gridView.setAdapter(adapter);
        
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	   public void onItemClick(AdapterView<?> parent, View v,
    			   int position, long id) {
    		   Toast.makeText(getApplicationContext(),
    				   ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    	   }
       });
       
    }
    
    @SuppressWarnings("unchecked")
	public void addGroupName(String groupName){
    	Groups group = datasource.createGroup(groupName);
    	ArrayAdapter<Groups> adapter =  (ArrayAdapter<Groups>) gridView.getAdapter();
    	adapter.add(group);
    	adapter.notifyDataSetChanged();
    }
    
	/** Called when the user selects to edit group*/
    public void launchEditGroup(Groups group) {
        // Do something in response to button.
    	Intent i = new Intent(this, EditGroup.class);
    	i.putExtra(MESSAGE, group);
    	startActivity(i);
    }
    
    /** Called when the user selects to add group*/
    public void launchAddGroup() {
    	Intent i = new Intent(this, AddGroup.class);
    	startActivity(i);
    }
    
}