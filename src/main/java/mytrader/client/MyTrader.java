/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrader.client;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 *
 * @author Braden
 */
public class MyTrader {
    
   private final Stack<Purchase> purchases;
    private final Stack<Sales> sales;
    private final Stack<String> tickers;
    private final Scanner in;
    private final BlockingQueue<Stock> stockBlocker;
    private final ArrayList<Stock> stocks;
    private Double funds; 
    
    public MyTrader(){
        this.funds=500.0;
        stockBlocker = new ArrayBlockingQueue<>(3);
        stocks = new ArrayList<>();
        this.in = new Scanner(System.in);
        this.tickers = new Stack<>();
        this.purchases = new Stack<>();
        this.sales = new Stack<>();
    }
    
    public MyTrader(Double funds){
    
        this.funds= funds;
        stockBlocker = new ArrayBlockingQueue<>(3);
        stocks = new ArrayList<>();
        this.in = new Scanner(System.in);
        this.tickers = new Stack<>();
        this.purchases = new Stack<>();
        this.sales = new Stack<>();
        
        //statically provide 3 stocks to test things
        tickers.add("CSCO");
        tickers.add("JNPR");
        tickers.add("VMW");
    }
    
     /**
     * Ask the user to enter three Stock tickers.
     */
    public void promptForTickers() {
        // Prompt the user to enter three Stock tickers.
        System.out.printf("You have been given $%.2f\n to invest.", funds);
        System.out.println("Please enter three stock tickers:");

        for (int i = 0; i < 3; i++) {
            System.out.print("  " + (i + 1) + ". ");
            tickers.add(in.next());
        }
    }
    
    /**
     * Ask the user to list the number of purchases to make.
     *
     * @throws Exception - Invalid purchasing was attempted.
     */
    public void promptForInvestment() throws Exception {
        for (Stock currentStock : stocks) {

            // Decide how much of the shares the user is allowed to Purchase.
            int numShares = funds.intValue() / currentStock.getLastValue().intValue();

            // Display the prompt.
            System.out.printf("Current Funds Left: $%.2f\n", funds);
            System.out.println("How many shares of " + currentStock.getSymbol() + " would you like to purchase?");
            System.out.println("You may purchase at most " + numShares + " shares.");
            System.out.print("> ");

            // Create a temporary container to store the number of shares they want to buy.
            int tmpPurchase = in.nextInt();

            // If the user does not want to Purchase any of the shares, or can't, just move on.
            if (tmpPurchase == 0) {
                continue;
            }

            // If the user tries to Purchase negative shares, throw an error.
            if (tmpPurchase < 0) {
                throw new Exception("You cannot purchase negative shares!");
            }

            // If the user tries to Purchase more than they are allowed, throw an error.
            if (tmpPurchase > numShares) {
                throw new Exception("You cannot purchase that many shares!");
            }

            // Add any purchases to the stack.
            purchases.add(new Purchase(currentStock, tmpPurchase));

            // Subtract any money that is invested from the starting amount.
            funds -= currentStock.getLastValue() * tmpPurchase;
        }
    }
//Written by Braden
    public void promptForSales() {
        /*assess list of tickers specified for sale then prompt for how
            many shares of each ticker to sell
            then display what the sale price is and 
         */
        //Scanner scanner = new Scanner(System.in);

        int numShares = 0;
        int salesCounter = 0;
        //while (purchases != null){
        System.out.println(purchases.size());

        for (Purchase item : purchases) {
            //System.out.println("Which stocks would you like to sell? :" + item.getStock().getSymbol());
            System.out.println("How many shares of this stock would you like to sell? There are : " + item.getShares() + " shares to sell.");
            numShares = in.nextInt();

            if (numShares != 0) {
                sales.add(new Sales(item.getStock(), numShares));
            }

            System.out.println("Stocks and shares sold : " + sales.get(salesCounter).getStock().getSymbol() + " Shares : " + sales.get(salesCounter).getShares() + "\n");
            numShares = 0;
            salesCounter++;
        }

        //System.out.println("Stocks and shares sold : " + sales.get(salesCounter));
        //sales.add(new SalesDB(Stock, numShares));
        //}
        // Showing difference with git.
    }
    
     /**
     * Sends an http request to the server.
     *
     * @param command - The command we wish to execute.
     * @param requestData - The stuff we want to append to the stream.
     * @throws MalformedURLException - The URL is invalid.
     */
    public void sendData(String command, Object requestData) throws MalformedURLException {

        // Create a hashmap to append to the output stream.
        HashMap<String, Object> servletRequest = new HashMap<>();

        // Append the command and the purchasing / selling data.
        servletRequest.put("command", command);
        servletRequest.put("data", requestData);

        // Create a runnable object.
        ServletComm communication = new ServletComm(servletRequest);

        // Create a new thread.
        Thread thread = new Thread(communication);

        // Run the request, and move on.
        thread.start();
    }

    /**
     * Obtains the data from the Google Finance API.
     *
     * @throws MalformedURLException - URL is invalid or ticker is not valid.
     * @throws InterruptedException - Thread was interrupted.
     */
    public void getData() throws MalformedURLException, InterruptedException {

        // For each ticker, get the Stock data.
        for (String currentTicker : tickers) {
            StockQuery query = new StockQuery(currentTicker, stockBlocker);
            Thread thread = new Thread(query);
            thread.start();
        }

        // Grab the data that is returned from each thread and place currentTicker into 
        // the stocks ArrayList.
        for (int i = 0; i < 3; i++) {
            stocks.add(stockBlocker.take());
        }
    }

    /**
     * The main program that is executed.
     *
     * @param args - Optional command-line parameters for the program.
     * @throws InterruptedException - A thread was interrupted.
     * @throws MalformedURLException - Invalid URL was formed from user input.
     * @throws Exception - Users attempted to Purchase invalid portions.
     */
    public static void main(String[] args) throws InterruptedException, MalformedURLException, Exception {
        MyTrader game = new MyTrader(1000.0);
        //game.promptForTickers();
        // Send threaded to requests to Google Finance API.
        game.getData();

        // Get the Stock information from the External Service (Requires Internet).
        game.promptForInvestment();

        game.sendData("updateStocks", game.stocks);
        
        //by Braden
        game.promptForSales();

        // Send the purchases to the server.
//        game.sendData("buy", game.purchases);
//        game.promptForSales();
       

    }
}
