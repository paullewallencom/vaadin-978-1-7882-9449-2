package com.packt.example.frontend;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ExampleUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		HorizontalLayout header = new HorizontalLayout();
		header.setWidth(100, Unit.PERCENTAGE);

		layout.addComponent(header);

		Label logoPlaceholder = new Label("Logo");
		Button logout = new Button("Logout");
		logout.setStyleName(ValoTheme.BUTTON_LINK);

		header.addComponents(logoPlaceholder, logout);
		header.setExpandRatio(logout, 1);
		header.setComponentAlignment(logout, Alignment.TOP_RIGHT);

		HorizontalLayout mainContent = new HorizontalLayout();
		mainContent.setSizeFull();

		layout.addComponent(mainContent);
		layout.setExpandRatio(mainContent, 1);

		VerticalLayout mainMenu = new VerticalLayout();
		mainMenu.setWidth(200, Unit.PIXELS);

		Panel viewArea = new Panel();
		viewArea.setSizeFull();
		viewArea.setStyleName(ValoTheme.PANEL_BORDERLESS);

		mainContent.addComponents(mainMenu, viewArea);
		mainContent.setExpandRatio(viewArea, 1);

		mainMenu.setMargin(false);

		Navigator navigator = new Navigator(this, viewArea);
		navigator.addView("", HomeView.class);
		navigator.addView("customers", CustomersView.class);
		navigator.addViewChangeListener(e -> onViewChange(e));

		mainMenu.addComponents(new Button("Home", e -> navigator.navigateTo("")),
				new Button("Customers", e -> navigator.navigateTo("customers")));

		setContent(layout);
	}

	private boolean onViewChange(ViewChangeEvent e) {
		return true;
	}

	@WebServlet(urlPatterns = "/*", name = "VaadinServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ExampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	protected void onLayoutClicked(LayoutClickEvent e) {
	}

}
