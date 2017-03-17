/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrader.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import qcJSON.*;

/**
 *
 * @author Braden
 */
public class ServletComm implements Runnable {

    private final URL ADDRESS;
    private final HashMap<String, Object> data;
    
    public ServletComm() throws MalformedURLException {
        this(new HashMap<String, Object>());
    }
    
  public ServletComm(HashMap<String, Object> data) throws MalformedURLException {
        ADDRESS = new URL("http://localhost:8080/DayTrader/DayTraderServer");
        this.data = data;
    }
    @Override
    public void run() {
        try {
            // Generate a new connection to be used
            HttpURLConnection connection = (HttpURLConnection) ADDRESS.openConnection();
            
            connection.setDoOutput(true);
            
            JSONOutputStream streamToServlet = new JSONOutputStream(connection.getOutputStream());
            
            streamToServlet.writeObject(data);
            
            JSONInputStream streamFromServlet = new JSONInputStream(connection.getInputStream());
            
            HashMap<String, Object> stockValues = (HashMap<String, Object>) streamFromServlet.readObject();
            
            System.out.println(stockValues);
        } 
        catch (JSONException | IOException | ParseException ex) {
            Logger.getLogger(ServletComm.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
}
