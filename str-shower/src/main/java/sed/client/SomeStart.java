package sed.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class SomeStart implements EntryPoint {

	public void onModuleLoad() {
		 RootPanel.get().add(new SomeForm());
	}

}
