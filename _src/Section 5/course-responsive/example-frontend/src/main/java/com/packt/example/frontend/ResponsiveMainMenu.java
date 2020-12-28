package com.packt.example.frontend;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class ResponsiveMainMenu extends Composite {

	private HorizontalLayout title;
	private CssLayout menuItems;
	private CssLayout valoMenuPart;
	private Label logo;

	public ResponsiveMainMenu() {
		CssLayout rootLayout = new CssLayout();
		rootLayout.setPrimaryStyleName(ValoTheme.MENU_ROOT);

		valoMenuPart = new CssLayout();
		valoMenuPart.setPrimaryStyleName(ValoTheme.MENU_PART);

		title = new HorizontalLayout();
		title.setWidth(100, Unit.PERCENTAGE);
		title.setStyleName(ValoTheme.MENU_TITLE);

		Button logout = new Button("Logout", VaadinIcons.SIGN_OUT);
		logout.addStyleName(ValoTheme.BUTTON_SMALL);
		logout.addStyleName("sign-out-title");

		title.addComponent(logout);
		title.setExpandRatio(logout, 1);
		title.setComponentAlignment(logout, Alignment.MIDDLE_RIGHT);

		logo = new Label(VaadinIcons.ROCKET.getHtml(), ContentMode.HTML);
		logo.setSizeUndefined();
		logo.setPrimaryStyleName(ValoTheme.MENU_LOGO);

		Button toggle = new Button(VaadinIcons.MENU);
		toggle.setStyleName("valo-menu-toggle");
		toggle.addStyleName(ValoTheme.BUTTON_SMALL);
		toggle.addClickListener(e -> {
			valoMenuPart.setStyleName("valo-menu-visible", !isMenuVisible());
		});

		menuItems = new CssLayout();
		menuItems.setPrimaryStyleName("valo-menuitems");

		rootLayout.addComponents(valoMenuPart);
		valoMenuPart.addComponents(title, toggle, logo, menuItems);

		setCompositionRoot(rootLayout);
	}

	private boolean isMenuVisible() {
		return valoMenuPart.getStyleName().contains("valo-menu-visible");
	}

	public void addMenuItem(String caption, Resource icon, String target) {
		Button menuItem = new Button(caption, icon);
		menuItem.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		menuItems.addComponent(menuItem);
		menuItem.addClickListener(e -> {
			getUI().getNavigator().navigateTo(target);
		});
	}

	public void closeMenu() {
		valoMenuPart.removeStyleName("valo-menu-visible");
	}
}
