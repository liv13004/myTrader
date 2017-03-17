/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrader.client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Braden
 */
public class StockQuery implements Runnable {


private BlockingQueue<Stock> stocks;
private final String ticker;


  
    public StockQuery(String ticker, BlockingQueue<Stock> stocks) {
     this.ticker = ticker;
     this.stocks = stocks;
     
    }

    @Override
    public void run() {
        URL stockURL = new URL("http://marketdata.websol.barchart.com/getQuote.json?key=6d6d849162ae4a65338089e08ec5b1c4&symbols=" + ticker);
        HttpURLConnection connection = HttpURLConnection(stockURL);
    }
    
}
