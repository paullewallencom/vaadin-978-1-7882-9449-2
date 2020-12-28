package com.packt.example.frontend;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
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

@Widgetset("com.packt.example.widgetset.ExampleWidgetset")
@Theme("example")
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
public class ExampleUI extends UI {

	private ResponsiveMainMenu mainMenu;

	public ExampleUI() {
		mainMenu = new ResponsiveMainMenu();
	}

	@Override
	protected void init(VaadinRequest request) {
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(false);

		HorizontalLayout mainContent = new HorizontalLayout();
		mainContent.setSizeFull();

		layout.addComponent(mainContent);
		layout.setExpandRatio(mainContent, 1);

		Button logout = new Button("Logout", VaadinIcons.SIGN_OUT);
		logout.addStyleName("sign-out-header");

		VerticalLayout viewAreaVertical = new VerticalLayout();
		viewAreaVertical.addComponent(logout);
		viewAreaVertical.setComponentAlignment(logout, Alignment.TOP_RIGHT);

		Panel viewArea = new Panel();
		viewArea.setSizeFull();
		viewArea.setStyleName(ValoTheme.PANEL_BORDERLESS);

		viewAreaVertical.addComponent(viewArea);
		viewAreaVertical.setExpandRatio(viewArea, 1);
		mainContent.addComponents(mainMenu, viewAreaVertical);
		mainContent.setExpandRatio(viewAreaVertical, 1);

		Navigator navigator = new Navigator(this, viewArea);
		navigator.addView("", HomeView.class);
		navigator.addView("customers", CustomersView.class);
		navigator.addViewChangeListener(e -> onViewChange(e));

		mainMenu.addMenuItem("Home", VaadinIcons.HOME, "");
		mainMenu.addMenuItem("Customers", VaadinIcons.USERS, "customers");

		setContent(layout);
	}

	private boolean onViewChange(ViewChangeEvent e) {
		mainMenu.closeMenu();
		return true;
	}

	@WebServlet(urlPatterns = "/*", name = "VaadinServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ExampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

	protected void onLayoutClicked(LayoutClickEvent e) {
	}

}
