package app;

import service.MenuService;

public class Main {
	private MenuService menuService = new MenuService();

	public static void main(String[] args) {
		Main mainObj = new Main();

		mainObj.menuService.startSession();
	}
}
