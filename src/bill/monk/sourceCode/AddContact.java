package bill.monk.sourceCode;

import android.app.Activity;
import android.os.Bundle;

public class AddContact extends Activity {

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add);
        classInteraction.setAddContact(this);
    }

}
