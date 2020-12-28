package com.packt.example.frontend;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		Label label = new Label("Home");
		addComponent(label);
	}

	@Override
	public void beforeLeave(ViewBeforeLeaveEvent event) {
		event.navigate();
	}

}
