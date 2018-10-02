package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Asset;
//import sun.applet.Main;

import java.util.List;
import java.util.Scanner;

public class AssetHomeView implements View {

    private int choice;
    private Request request;
    private List<Asset> listAsset;
    
    public void showResults(Request request) {
    	this.request = request;
    	if (this.request.get("message") != null) {
    		System.out.println(this.request.get("message"));
    	}	        this.listAsset = (List<Asset>) this.request.get("visualizzaAssets");
        System.out.println();
        System.out.println();
        System.out.println("Asset Management Base");
    	System.out.println();
    	System.out.println("Gestione Asset");
    	System.out.println();
    	System.out.println("----- ASSET DISPONIBILI -----");
        System.out.println();
        this.listAsset.forEach(asset -> System.out.println(asset));
        System.out.println();

    }

    public void showOptions() {
    	System.out.println("-------MENU-------");
        System.out.println("");
        System.out.println("1) Inserimento Asset");
        System.out.println("2) Modifica Asset");
        System.out.println("3) Cancellazione Asset");
        System.out.println("4) Indietro");
        this.choice = Integer.parseInt(getInput());
    }

    public void submit() {
        if (choice < 1 || choice > 4) {
        	String username = this.request.get("username").toString();
        	this.request = new Request();
        	this.request.put("username", username);
        	this.request.put("choice", "assetsManagement");
            MainDispatcher.getInstance().callAction("Asset", "doControl", this.request);
        }
        else if (choice == 4) {
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        }
        else {
        	Request request = new Request();
        	if (this.choice == 1) {
        		request.put("choice", "insert");
        	}
        	else if (this.choice == 2) {
        		request.put("choice", "update");
        	}
        	else if (this.choice == 3) {
        		request.put("choice", "delete");
        	}
        	MainDispatcher.getInstance().callAction("Asset", "doControl", request);        		
        }
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
