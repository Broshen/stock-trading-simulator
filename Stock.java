public class Stock {
	
	double price;
	int unitsForSale, unitsOnPlayer, chance, cycle, cyclelength;
	String name, message;
	String positive [];
	String negative [];
	
	public Stock (double p, int u, String n){
		price=p;
		unitsForSale=u;
		unitsOnPlayer=0;
		name = n;
		
		cycle=(int)(Math.random()*2); //0 is expansion, 1 is recession
		cyclelength=(int)(Math.random()*50+20);
				
		positive = new String [] {name+" has released a new product.", name + " has expanded into a new country.", "new regulations have made " + name + "'s products cheaper.", name + "'s competitor has gone out of business", name + " has been publicly endorsed by Robert Downey Jr."};
		negative = new String [] {name+"'s latest product is a flop", name + " has been hit by corruption scandals", name + "'s locations in Cuba have gone out of business", name + "'s products have decreased in quality", name + " has let off 3000 workers"};
		
	}
	
	public void PriceChange(){
		
		cyclelength--;
		
		if(cyclelength==0){
			cycle=(int)(Math.random()*2); //0 is expansion, 1 is recession
			cyclelength=(int)(Math.random()*50+20);
		}

		if(cycle==0)
			price+=Math.random()*100-30;
		
		else if (cycle==1)
			price-=Math.random()*100-30;
					
		message=this.EventSimulator();
		
		if(price<1)
			price=50;
		
		if(price>1000)
			price=950;
		
	}
	
	public String EventSimulator (){
		
		chance=(int)(Math.random()*30);
		
		if(chance==1)//choose a good event
		{
			price*=1+Math.random()*0.7;
			return positive[(int)(Math.random()*(positive.length))];
			
		}
			
		if(chance==2)//chose a bad event
		{
			price*=1-Math.random()*0.7;
			return negative[(int)(Math.random()*(negative.length))];
			
		}
			
		else
			return " ";
		
	}
	
	
}
