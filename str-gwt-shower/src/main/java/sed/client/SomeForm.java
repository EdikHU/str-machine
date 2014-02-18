package sed.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class SomeForm extends Composite implements HasText {

	private static SomeFormUiBinder uiBinder = GWT
			.create(SomeFormUiBinder.class);

	interface SomeFormUiBinder extends UiBinder<Widget, SomeForm> {
	}

	public SomeForm() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	public SomeForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("here " +new Date());
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

}
