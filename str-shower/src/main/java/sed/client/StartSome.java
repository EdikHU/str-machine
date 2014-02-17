package sed.client;



import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;


public class StartSome implements EntryPoint{

	@Override
	public void onModuleLoad() {
		Button butt=new Button("Butt");
		RootPanel.get("some1").add(butt);
		butt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				Window.alert("!!! 3 "+new Date());
				System.out.println("here "+ new Date());
			}
		});
	}

}
