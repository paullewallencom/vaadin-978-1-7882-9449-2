package com.packt.example.frontend;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class ExampleUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		setContent(new Label("You've reached the UI"));
	}

	@WebServlet(urlPatterns = "/*", name = "VaadinServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ExampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
