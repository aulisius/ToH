import java.io.*;

class discStack {

	int[] discArray = new int[3];
	int top = 0;

	public boolean isEmpty(){ return top == 0; }

	public boolean isFull(){ return top == 3; }

	public void push(int x) { if (top < 3) discArray[top++] = x; }

	public int size() { return top; }

	public void pop() { if (top > 0) top--; }

	public int peek(){ return discArray[top - 1]; }
}

class Tower {

	discStack poleA = new discStack(), 
			  poleB = new discStack(), 
			  poleC = new discStack();

	int sizeA, 
		sizeB, 
		sizeC,
		ActiveDisc, 
		CurrentDisc, 
		check;

	static int count;

	int[] val = {3, 2, 1};

	char PickUp, 
		 DropOut;

	public void dispStack(discStack stack)	{

		int a[] = new int[3];

		int size = stack.size();

		if (size > 0) {

			for (int i = (size - 1); i >= 0; i--) {

				a[i] = stack.peek (); 
				stack.pop ();

			}

			for (int i = 0; i < size; i++) {

				System.out.print (a[i] + " " );
				stack.push (a[i]);

			}

			System.out.println ();
		}
		else
			System.out.println ("empty");
	}

	public void userInput() {

		System.out.print("Enter pole to pick and drop: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
	
			String input = br.readLine();
			input = input.replaceAll("\\s", "");

			if(!input.isEmpty() && input.length() == 2) {
	
				PickUp = input.charAt(0);
				DropOut = input.charAt(1);
			}
			else 
				PickUp = DropOut = ' ';
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public discStack getPole(char poleID) {
		
		if(poleID == 'a' || poleID == 'A') return poleA;
		
		if(poleID == 'b' || poleID == 'B') return poleB;
		
		if(poleID == 'c' || poleID == 'C') return poleC;
	
		return null;
	}

	public void setPole(char poleID, discStack pole) {
	
		if(poleID == 'a' || poleID == 'A') poleA = pole;
		
		if(poleID == 'b' || poleID == 'B') poleB = pole;
		
		if(poleID == 'c' || poleID == 'C') poleC = pole;
	
	}

	public boolean discPop() {

		boolean complete = false;

		if (PickUp != DropOut) {

			discStack pole = getPole (PickUp);

			if (pole != null) {
			
				if(!pole.isEmpty ()) {
				
					ActiveDisc = pole.peek ();
					pole.pop ();
					complete = true;
					setPole (PickUp, pole);
				}
				else 
					System.out.println ("Wrong Input! Pole " + PickUp + " is empty!");
			
			}
			else 
				System.out.println ("Column not found!");
		}

		return complete;
	}

	public boolean discPush() {
	
		boolean complete = false;
		
		discStack pole = getPole(DropOut);
		
		if(pole != null) {
				if (!pole.isEmpty()) {
				
					CurrentDisc = pole.peek();
					
					if(CurrentDisc > ActiveDisc) {
					
						pole.push(ActiveDisc);
						complete = true;
						setPole(DropOut, pole);
					
					}
					else {
						
						discStack pickupPole = getPole(PickUp);
						pickupPole.push(ActiveDisc);
						setPole(PickUp, pickupPole);
						
						System.out.println("Rule Break : " + CurrentDisc + " is less than " + ActiveDisc);
					}
				}
				else {
					pole.push(ActiveDisc);
					setPole(DropOut, pole);
				}
		}
		else {
				
			System.out.println("Column not found!");
			
			discStack temp = getPole(PickUp);
			temp.push(ActiveDisc);

			setPole(PickUp, temp);

		}
	
		return complete;
	
	}

	public boolean checkResult() {
	
		int a[] = new int[3];
		
		check = 0;
		
		int size = poleC.size();
		if (!poleC.isEmpty () && size == 3) {
		
			for (int i = size - 1; i >= 0; i--) {
			
				a[i] = poleC.peek ();
				poleC.pop ();

			}
			
			for (int i = 0; i < size; i++) poleC.push (a[i]);
			
			for (int i = 0; i < 3; i++) 			
				if (a[i] == val[i])
					check++;
			
		}

		return check == 3;
	}

	public static void move() { count++; }

	public void result() {
	
		System.out.println("The game is over!");
		
		System.out.println("Total moves: " + count);
		
		if (count <= 7)
			System.out.println("Wow! You are good at this!");
		else if (count <= 13)
			System.out.println("Not bad, mate");
		else
			System.out.println("Err...you have no idea what to do, do you?");
	}

	public void displayTower() {
		
		System.out.print("Pole A: ");
		dispStack (poleA);

		System.out.print("Pole B: ");
		dispStack (poleB);
		
		System.out.print("Pole C: ");
		dispStack (poleC);

	}

	public void setGame() {
		poleA.push (3);
		poleA.push (2);
		poleA.push (1);
	}

	public static void main(String[] args) {
	
		Tower tower = new Tower ();
		
		tower.setGame ();
		tower.displayTower ();
		
		while (!tower.checkResult ()) {
		
			tower.userInput();
			
			if (tower.discPop ()) {
				tower.discPush ();
				move ();
			}

			tower.displayTower ();
		}

		tower.result();
	}
}
