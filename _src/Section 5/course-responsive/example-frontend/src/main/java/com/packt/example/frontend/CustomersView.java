package com.packt.example.frontend;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CustomersView extends VerticalLayout implements View {

	public CustomersView() {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Label label = new Label("Customers");
		addComponent(label);
	}

	@Override
	public void beforeLeave(ViewBeforeLeaveEvent event) {
		Window window = new Window("Are you sure?");
		HorizontalLayout content = new HorizontalLayout();
		window.setContent(content);

		Button yes = new Button("Yes", e -> {
			event.navigate();
			window.close();
		});
		Button no = new Button("No", e -> window.close());

		content.addComponents(yes, no);

		UI.getCurrent().addWindow(window);
		window.center();
		window.setModal(true);
	}
}
