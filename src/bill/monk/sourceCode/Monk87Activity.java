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
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
	ArrayAdapter<Groups> adapter;

	private GridView gridView;

	ArrayList<Groups> groupList;

	@Override
	public void registerForContextMenu(View view) {
		// TODO Auto-generated method stub
		super.registerForContextMenu(view);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	/*	groupList.removeAll(groupList);
		groupList.addAll(datasource.getAllGroups());
		adapter.notifyDataSetChanged();
		Toast.makeText(getApplicationContext(),
				"Resume",
			   Toast.LENGTH_SHORT).show();
	 */
		Toast.makeText(getApplicationContext(),
				   "OnResume",
				   Toast.LENGTH_SHORT).show();
	}
	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId()==R.id.gridViewGrp) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			menu.setHeaderTitle(groupList.get(info.position).toString());
			String[] menuItems = getResources().getStringArray(R.array.menuItems);
			for (int i = 0; i<menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex = item.getItemId();
		Groups group = (Groups) gridView.getItemAtPosition(info.position);

		switch (menuItemIndex) {
		case 0:
			// Edit group.
			launchEditGroup(group);
			break;

		case 1:
			// Delete group.
			datasource.deleteGroups(group);
			adapter = (ArrayAdapter<Groups>) gridView.getAdapter();
			adapter.remove(group);
			adapter.notifyDataSetChanged();
			break;
		case 2:
			// Flush it.

			break;
		default:
			break;
		}
		return true;
	}

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
		adapter = new ArrayAdapter<Groups>(this, android.R.layout.simple_list_item_1, groupList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				launchEditGroup((Groups) gridView.getItemAtPosition(position));
			}
		});

		gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int arg2, long arg3) {
				return false;
			}
		});
		registerForContextMenu(gridView);
	}

	@SuppressWarnings("unchecked")
	public void addGroupName(String groupName){
		Groups group = datasource.createGroup(groupName);
		adapter =  (ArrayAdapter<Groups>) gridView.getAdapter();
		adapter.add(group);
		adapter.notifyDataSetChanged();
	}
	
	@SuppressWarnings("unchecked")
	public void editGroupName(Groups group) {
		if (datasource.editGroupName(group) > 0) {
			adapter = (ArrayAdapter<Groups>) gridView.getAdapter();
			adapter.remove(group);
			adapter.add(group);
			adapter.notifyDataSetChanged();
		}
	}

	/** Called when the user selects to edit group*/
	public void launchEditGroup(Groups group) {
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