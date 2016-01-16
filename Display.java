import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.*;  // Needed for ChangeListener

////////////////////////////////////////////////////////// Demonstrates display of graphics on a panel
/**
 * @author Boshen
 *
 */
/**
 * @author Boshen
 *
 */
class Graph2 extends JFrame
{
	//Buttons
	JButton timerButton, buyButton1, sellButton1, buyButton2, sellButton2, buyButton3, sellButton3;

	//textFields
	//buying and selling inputs
	JTextField _buy1 = new JTextField (5);
	JTextField _sell1 = new JTextField (5);
	JTextField _buy2 = new JTextField (5);
	JTextField _sell2 = new JTextField (5);
	JTextField _buy3 = new JTextField (5);
	JTextField _sell3 = new JTextField (5);

	//display for the stocks owned, stocks price, number of stocks available for sale
	JTextField _stockOwned1 = new JTextField (5);
	JTextField _stockOwned2 = new JTextField (5);
	JTextField _stockOwned3 = new JTextField (5);
	JTextField _stockPrice1 = new JTextField (5);
	JTextField _stockPrice2 = new JTextField (5);
	JTextField _stockPrice3 = new JTextField (5);
	JTextField _stockAvailable1 = new JTextField (9);
	JTextField _stockAvailable2 = new JTextField (9);
	JTextField _stockAvailable3 = new JTextField (9);

	//display for money and headlines
	JTextField _money = new JTextField (7);
	JTextArea _headline = new JTextArea (30,30);

	int size = 150; // initial size of square
	Timer t;

	//price stuff

	Stock AppleS = new Stock (20, 30000, "Apple");
	Stock MicrosoftS = new Stock (20, 2300000, "Microsoft");
	Stock GoogleS = new Stock (20, 36000000, "Google");

	//user inventory
	double userMoney=1000;

	//variables used for transactions
	int amount, transactionSuccess;
	double transactionMoney;
	String transactionText = " ";

	//storing the prices
	public double[] myArray1 = new double [999999];
	public double[] myArray2 = new double [999999];
	public double[] myArray3 = new double [999999];

	//storing events
	public String[] textArray1 = new String [999999];
	public String[] textArray2 = new String [999999];
	public String[] textArray3 = new String [999999];

	//clock
	public int val = 0;


	/*========================constructor==========================*/
	public Graph2 ()
	{
		//timer
		timerButton = new JButton ("Start Timer");
		Automatic auto = new Automatic ();
		t = new Timer (2000, auto); // set up automatic timer - magneto will move every half second

		//text in buttons
		buyButton1 = new JButton ("Buy Apple Stock");
		sellButton1 = new JButton ("Sell Apple Stock");
		buyButton2 = new JButton ("Buy Microsoft Stock");
		sellButton2 = new JButton ("Sell Microsoft Stock");
		buyButton3 = new JButton ("Buy Google Stock");
		sellButton3 = new JButton ("Sell Google Stock");       

		//Create content pane, panels, set layouts
		JPanel content = new JPanel ();        // Create a content pane
		content.setLayout (new BorderLayout ()); // Use BorderLayout for main panel
		JPanel north = new JPanel ();
		north.setLayout (new FlowLayout ()); // Use FlowLayout for input area
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel east = new JPanel ();
		east.setLayout (new BorderLayout ()); // Use FlowLayout for input area
		JPanel northeast = new JPanel ();
		northeast.setLayout (new FlowLayout ());
		JPanel southeast = new JPanel ();
		southeast.setLayout (new FlowLayout ());
		JPanel centreeast = new JPanel ();
		centreeast.setLayout (new FlowLayout ());
		JScrollPane scroll = new JScrollPane (_headline);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		DrawArea board = new DrawArea (850, 400); // Custom panel to use as graphics output area

		//displays
		_headline.setEditable(false);        
		_money.setEditable(false);
		_money.setText( userMoney+"");
		_stockOwned1.setEditable(false);
		_stockOwned1.setText(AppleS.unitsOnPlayer+"");
		_stockOwned2.setEditable(false);
		_stockOwned2.setText(MicrosoftS.unitsOnPlayer+"");
		_stockOwned3.setEditable(false);
		_stockOwned3.setText(GoogleS.unitsOnPlayer+"");
		_stockPrice1.setEditable(false);
		_stockPrice1.setText(AppleS.price+"");
		_stockPrice2.setEditable(false);
		_stockPrice2.setText(MicrosoftS.price+"");
		_stockPrice3.setEditable(false);
		_stockPrice3.setText(GoogleS.price+"");
		_stockAvailable1.setEditable(false);
		_stockAvailable1.setText(AppleS.unitsForSale+"");
		_stockAvailable2.setEditable(false);
		_stockAvailable2.setText(MicrosoftS.unitsForSale+"");
		_stockAvailable3.setEditable(false);
		_stockAvailable3.setText(GoogleS.unitsForSale+"");

		//card 1
		JPanel card1 = new JPanel() {
			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += 100;
				return size;
			}
		};

		//card 2
		JPanel card2 = new JPanel() {
			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += 100;
				return size;
			}
		};

		//card 3
		JPanel card3 = new JPanel() {
			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += 100;
				return size;
			}
		};

		//Add the components to the input area.               
		card1.add (buyButton1);
		buyButton1.addActionListener (new ButtonListener ());
		card1.add (_buy1); 
		card1.add (sellButton1);
		sellButton1.addActionListener (new ButtonListener ());
		card1.add (_sell1);
		card1.add (new JLabel ("Stock Owned:")); // Create, add label
		card1.add (_stockOwned1);
		card1.add (new JLabel ("Current Price:"));
		card1.add(_stockPrice1);
		card1.add (new JLabel ("Units Available:"));
		card1.add(_stockAvailable1);
		tabbedPane.addTab("Apple", card1);
		tabbedPane.setForegroundAt(0, Color.MAGENTA);

		card2.add (buyButton2);
		buyButton2.addActionListener (new ButtonListener ());
		card2.add (_buy2); 
		card2.add (sellButton2);
		sellButton2.addActionListener (new ButtonListener ());
		card2.add (_sell2);
		card2.add (new JLabel ("Stock Owned:")); // Create, add label
		card2.add (_stockOwned2);
		card2.add (new JLabel ("Current Price:")); // Create, add label
		card2.add(_stockPrice2);
		card2.add (new JLabel ("Units Available:"));
		card2.add(_stockAvailable2);
		tabbedPane.addTab("Microsoft", card2);
		tabbedPane.setForegroundAt(1, Color.GREEN);

		card3.add (buyButton3);
		buyButton3.addActionListener (new ButtonListener ());
		card3.add (_buy3); 
		card3.add (sellButton3);
		sellButton3.addActionListener (new ButtonListener ());
		card3.add (_sell3);
		card3.add (new JLabel ("Stock Owned:")); // Create, add label
		card3.add (_stockOwned3);
		card3.add (new JLabel ("Current Price:"));
		card3.add(_stockPrice3);
		card3.add (new JLabel ("Units Available:"));
		card3.add(_stockAvailable3);
		tabbedPane.addTab("Google", card3);
		tabbedPane.setForegroundAt(2, Color.BLUE);

		north.add (timerButton);  
		timerButton.addActionListener (new ButtonListener ());

		northeast.add (new JLabel ("Money Owned:")); // Create, add label
		northeast.add (_money);// Add input field  
		centreeast.add (new JLabel ("News and Updates:")); // Create, add label
		southeast.add (scroll);

		east.add(northeast, BorderLayout.NORTH);
		east.add(southeast, BorderLayout.SOUTH);
		east.add(centreeast, BorderLayout.CENTER);

		content.add (north, "North"); // Input area
		content.add (board, "Center"); // Output area
		content.add(tabbedPane, BorderLayout.SOUTH);
		content.add (east, "East");

		//Set this window's attributes.
		setContentPane (content);
		pack ();
		setTitle ("Awesome Graph of Awesomeness");
		setSize (1130, 700);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo (null);           // Center window.

	}

	/*=================buttonlistener for inputs===================*/
	class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//timer events
			if (e.getActionCommand ().equals ("Start Timer")) 
			{
				timerButton.setText ("Stop Timer");
				t.start ();
			}
			else if (e.getActionCommand ().equals ("Stop Timer")) 
			{
				timerButton.setText ("Start Timer");
				t.stop ();
			}

			//stock events
			//buy events
			if(e.getActionCommand ().equals ("Buy Apple Stock"))
			{
				buyStock(_buy1, AppleS, myArray1);
			}

			if(e.getActionCommand ().equals ("Buy Microsoft Stock"))
			{
				buyStock(_buy2, MicrosoftS, myArray2);
			}

			if(e.getActionCommand ().equals ("Buy Google Stock"))
			{
				buyStock(_buy3, GoogleS, myArray3);
			}

			//sell events
			if(e.getActionCommand ().equals ("Sell Apple Stock"))
			{
				sellStock(_sell1, AppleS, myArray1);
			}

			if(e.getActionCommand ().equals ("Sell Microsoft Stock"))
			{
				sellStock(_sell2, MicrosoftS, myArray2);
			}

			if(e.getActionCommand ().equals ("Sell Google Stock"))
			{
				sellStock(_sell3, GoogleS, myArray3);
			}
		}

		/*==============functions used to buy and sell stocks==============*/
		public void buyStock (JTextField buyButton, Stock S, double[] PriceArray){

			//set the transactionSuccess to fail initially
			transactionSuccess=1;

			//if user input is an integer, transaction is a success
			try{
				amount=Integer.parseInt(buyButton.getText());
				transactionSuccess=0;
			}

			//catch exceptions for if input is not an integer
			catch(Exception a){
				transactionText+="\nInvalid Input";
				_headline.setText(transactionText); 
				transactionSuccess=1;
			}

			//if input is successfully parsed
			if(transactionSuccess==0){
				//calculate the amount of money being transacted
				transactionMoney=amount*PriceArray[val];

				//check to see that the user has not overspent, or has bought all the stocks, or has bought negative stocks
				if(userMoney-transactionMoney<0 || (S.unitsOnPlayer+amount)>S.unitsForSale || amount<0)
				{
					transactionText+="\nTransaction Failed!";
					_headline.setText(transactionText); 
				}

				//if input is valid, move stock to user inventory, take away money
				else{
					transactionText+="\nTransaction Complete.";
					_headline.setText(transactionText); 
					userMoney-=transactionMoney;
					S.unitsForSale-=amount;
					S.unitsOnPlayer+=amount;
				}
			}
		}

		public void sellStock (JTextField sellButton, Stock S, double[] PriceArray){

			//set the transactionSuccess to fail initially
			transactionSuccess=1;

			//if user input is an integer, transaction is a success
			try{
				amount=Integer.parseInt(sellButton.getText());
				transactionSuccess=0;
			}

			//catch exceptions for if input is not an integer
			catch(Exception a){
				transactionText+="\nInvalid Input";
				_headline.setText(transactionText); 
				transactionSuccess=1;
			}

			//if input is successfully parsed
			if(transactionSuccess==0){
				//calculate the amount of money being transacted
				transactionMoney=amount*PriceArray[val];

				//check to see that the user has sold negative stocks, tried to sell more stocks than owned, or than available
				if((S.unitsOnPlayer-amount)<0 || amount<0||(S.unitsOnPlayer+amount)>S.unitsForSale)
				{
					transactionText+="\nTransaction Failed!";
					_headline.setText(transactionText); 
				}

				//if input is valid, move stock to user inventory, take away money
				else{
					transactionText+="\nTransaction Complete.";
					_headline.setText(transactionText); 
					userMoney+=transactionMoney;
					S.unitsForSale+=amount;
					S.unitsOnPlayer-=amount;
				}
			}
		}
	}

	/*===========timer event to update values at each tick=========*/
	class Automatic implements ActionListener
	{
		public void actionPerformed (ActionEvent event)  // Code to be executed by timer
		{
			repaint (); // Refresh drawing area (call paintComponent)

			//refresh stock prices, units for sale, money player owns, and any in game events
			_stockPrice1.setText(myArray1[val]+"");
			_stockPrice2.setText(myArray2[val]+"");
			_stockPrice3.setText(myArray3[val]+"");
			_stockAvailable1.setText(AppleS.unitsForSale+"");
			_stockAvailable2.setText(MicrosoftS.unitsForSale+"");
			_stockAvailable3.setText(GoogleS.unitsForSale+"");

			_money.setText((double)Math.round(userMoney*100)/100+"");
			_stockOwned1.setText(AppleS.unitsOnPlayer+"");
			_stockOwned2.setText(MicrosoftS.unitsOnPlayer+"");
			_stockOwned3.setText(GoogleS.unitsOnPlayer+"");

			if(textArray1[val]!=" "){
				transactionText+="\n"+textArray1[val];
				_headline.setText(transactionText);
			}

			if(textArray2[val]!=" "){
				transactionText+="\n"+textArray2[val];
				_headline.setText(transactionText);
			}

			if(textArray3[val]!=" "){
				transactionText+="\n"+textArray3[val];
				_headline.setText(transactionText);
			}


		}
	}

	/*==============calculate values and draws graph===============*/
	class DrawArea extends JPanel
	{

		//method to generate and fill arrays with stock prices for the entire game
		public void fillArray (double[] array, String[] sArray, Stock C)
		{
			for (int x = 0 ; x < array.length ; x++)
			{
				C.PriceChange();//generate the price of the stock based on the economy
				
				//stores the stock price
				array[x] = (double)Math.round(C.price*100)/100;
				
				//stores news item that is generated
				sArray[x]= C.message;

			}
		}

		public DrawArea (int width, int height)  // Create panel of given size
		{
			this.setPreferredSize (new Dimension (width, height));
		}

		public void paintComponent (Graphics g)  // g can be passed to a class method
		{
			//pre-generate all values using fillarray method on the first tick
			if(val<1)
			{
				fillArray(myArray1, textArray1, AppleS);     
				fillArray(myArray2, textArray2, MicrosoftS);   
				fillArray(myArray3, textArray3, GoogleS);     
			}        	
			
			//increase the tick everytime the paint method is called
			val++;

			//graph
			g.drawRect(50, 100, 850, 400);

			for(int z = 100; z <= 500; z+=10)
			{
				g.drawLine(25, z, 75, z);
			}

			int x1 = 50, x2 = 50, y1 = 0, y2 = 500;
			int v1 = 50, v2 = 50, w1 = 0, w2 = 500;
			int t1 = 50, t2 = 50, u1 = 0, u2 = 500;

			for(int y=0; y<17; y++)
			{
				//stock 1
				g.setColor(Color.MAGENTA);
				x1 = x1 + 50;
				y1 = 500 - (int)(myArray1[y+val] / 2.5);
				g.drawLine(x1, y1, x2, y2);
				x2 = x1;
				y2 = y1;	

				//stock 2
				g.setColor(Color.GREEN);
				v1 = v1 + 50;
				w1 = 500 - (int)(myArray2[y+val] / 2.5);
				g.drawLine(v1, w1, v2, w2);
				v2 = v1;
				w2 = w1;

				//stock 3
				g.setColor(Color.BLUE);
				t1 = t1 + 50;
				u1 = 500 - (int)(myArray3[y+val] / 2.5);
				g.drawLine(t1, u1, t2, u2);
				t2 = t1;
				u2 = u1;
			}

			//debugging
			System.out.println(myArray1[val] + " "+ myArray2[val] + " " +myArray3[val]);

		}
	}

}
