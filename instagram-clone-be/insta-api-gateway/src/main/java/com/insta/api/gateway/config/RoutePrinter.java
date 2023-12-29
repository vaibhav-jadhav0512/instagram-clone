package com.insta.api.gateway.config;

import java.util.List;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;

@Component
public class RoutePrinter {

	private final RouteLocator routeLocator;

	public RoutePrinter(RouteLocator routeLocator) {
		this.routeLocator = routeLocator;
		printRoutes();
	}

	private void printRoutes() {
		List<Route> routes = routeLocator.getRoutes().collectList().block();

		if (routes != null) {
			System.out.println("Configured Routes:");
			for (Route route : routes) {
				System.out.println("ID: " + route.getId());
				System.out.println("URI: " + route.getUri());
				System.out.println("Predicates: " + route.getPredicate());
				System.out.println("Filters: " + route.getFilters());
				System.out.println();
			}
		} else {
			System.out.println("No routes found.");
		}
	}
}