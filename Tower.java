import java.io.*;

class Discstack{
	int[] discArray = new int[3];
	int top = 0;

	public boolean isEmpty(){
		return (top == 0) ? true : false;
	}

	public boolean isFull(){
		return (top == 3) ? true : false;
	}
	public void push(int x){
		if(top<3)
			discArray[top++] = x;
	}
	public int size(){
		return top;
	}
	public void pop(){
		if(top > 0)
			top = top-1;
	}
	public int peek(){
		return discArray[top-1];
	}
}

class Tower {
	Discstack A = new Discstack(),B = new Discstack(),C = new Discstack();
	int sizeA, sizeB, sizeC, ActiveDisc, CurrentDisc, check;
	static int count;
	int[] val = {3,2,1};
	char PickUp, DropOut;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public void dispA()	{
		int a[] = new int[3];
		int i = 0,j = A.size();
		if (A.size() > 0) {
			for (i = (A.size() - 1);i >= 0;i--)
			{
				a[i] = A.peek(); 
				A.pop();
			}
			for (i = 0;i <= (j - 1);i++)
			{
				System.out.print(a[i] + " ");
				A.push(a[i]);
			}
			System.out.println("");
		}
		else
			System.out.println("empty");
	}
	public void dispB()	{
		int a[]= new int[3];
		int i = 0, j = B.size();
		if(B.size() >0) {
			for (i = (B.size() - 1);i >= 0 ;i--)
			{
				a[i] = B.peek();
				B.pop();
			}
			for (i = 0 ;i <= (j - 1);i++)
			{
				System.out.print(a[i] + " ");
				B.push(a[i]);
			}
			System.out.println(" ");
		}
		else
			System.out.println("empty");
	}
	public void dispC()	{
		int a[]= new int[3];
		int i = 0, j = C.size();
		if(C.size()>0) {
			for (i = (C.size() - 1);i >= 0 ;i--)
			{
				a[i] = C.peek();
				C.pop();
			}
			for (i = 0;i <= (j - 1) ;i++)
			{
				System.out.print(a[i] + " ");
				C.push(a[i]);
			}
			System.out.println(" ");
		}
		else
			System.out.println("empty");
	}
	public void userInput()	{
		System.out.print("Enter pole to pick and drop: ");
		String temp = new String();
		try{
			temp = in.readLine();
		}
		catch(IOException e){
			System.out.print("Error" + e);
		}
		temp = temp.replaceAll("\\s","");
		if(!temp.isEmpty() && temp.length() == 2){
			PickUp = temp.charAt(0);
			DropOut = temp.charAt(1);
		}
		else {
			PickUp = DropOut = ' ';
		}
	}
	public boolean discPop() {
		boolean complete = false;
		if (PickUp != DropOut) {
			switch (PickUp) {
				case 'a':
				case 'A':
				if (!A.isEmpty()) {
					ActiveDisc = A.peek();
					A.pop();
					complete = true;
				}
				else
					System.out.println("Wrong Input");
				break;
				case 'b':
				case 'B':
				if (!B.isEmpty()) {
					ActiveDisc = B.peek();
					B.pop();
					complete = true;
				}
				else
					System.out.println("Wrong Input");
				break;
				case 'c':
				case 'C':
				if (!C.isEmpty()) {
					ActiveDisc = C.peek();
					C.pop();
					complete = true;
				}
				else
					System.out.println("Wrong Input");
				break;
				default:
				System.out.println("Column not found");
			}
		}
		return complete;
	}
	public boolean discPush() {
		boolean complete = false;
		switch (DropOut) {
			case 'a' :
			case 'A' :
			if (!A.isEmpty()) {
				CurrentDisc = A.peek();
				if(CurrentDisc > ActiveDisc) {
					A.push(ActiveDisc);
					complete = true;
				}
				else {
					if (PickUp == 'B' || PickUp == 'b')
						B.push(ActiveDisc);
					if (PickUp == 'C' || PickUp == 'c')
						C.push(ActiveDisc);
					System.out.println("Rule Break : " + CurrentDisc + " is smaller than " + ActiveDisc);
				}
			}
			else
				A.push(ActiveDisc);
			break;
			case 'b' :
			case 'B' :
			if (!B.isEmpty()) {
				CurrentDisc = B.peek();
				if(CurrentDisc > ActiveDisc) {
					B.push(ActiveDisc);
					complete = true;
				}
				else {
					if (PickUp == 'C' || PickUp == 'c')
						B.push(ActiveDisc);
					if (PickUp == 'A' || PickUp == 'a')
						A.push(ActiveDisc);
					System.out.println("Rule Break : " + CurrentDisc + " is smaller than " + ActiveDisc);
				}
			}
			else
				B.push(ActiveDisc);
			break;
			case 'c' :
			case 'C' :
			if (!C.isEmpty()) {
				CurrentDisc = C.peek();
				if (CurrentDisc > ActiveDisc) {
					C.push(ActiveDisc);
					complete = true;
				}
				else {
					if (PickUp == 'A' || PickUp == 'a')
						A.push(ActiveDisc);
					if(PickUp == 'B' || PickUp == 'b')
						B.push(ActiveDisc);
					System.out.println("Rule Break : " + CurrentDisc + " is smaller than " + ActiveDisc);
				}
			}
			else
				C.push(ActiveDisc);
			break;
			default:
			System.out.println("Error 404:Column not found");
			if(PickUp == 'C' || PickUp == 'c')
				C.push(ActiveDisc);
			if (PickUp == 'A' || PickUp == 'a')
				A.push(ActiveDisc);
			if(PickUp == 'B' || PickUp == 'b')
				B.push(ActiveDisc);

		}
		return complete;
	}
	public boolean checkResult() {
		int a[] = new int[3], i, j;
		check = 0;
		j = C.size();
		if ((!C.isEmpty()) && (j == 3)) {
			for (i = (C.size() - 1);i >= 0;i--) {
				a[i] = C.peek();
				C.pop();
			}
			for (i = 0;i < j;i++)
				C.push(a[i]);
			for (i = 0;i < 3;i++) {
				if (a[i] == val[i])
					check++;
			}
		}
		return (check == 3) ? true : false ;
	}
	public static void move() {
		count++;
	}
	public boolean result() {
		System.out.println("The game is over");
		System.out.println("Total moves :" + count);
		if (count <= 7)
			System.out.println("Wow ! You are good at this");
		else if (count <= 13)
			System.out.println("Not bad, mate");
		else
			System.out.println("Err...you have no idea what to do, do you?");
		return false;
	}
	public void displayTower() {
		System.out.print("A : ");
		dispA();
		System.out.print("B : ");
		dispB();
		System.out.print("C : ");
		dispC();
	}
	public void setGame() {
		A.push(3);
		A.push(2);
		A.push(1);
	}

	public static void main(String[] args) {
		boolean i,j,k,l = true;
		Tower One = new Tower();
		One.setGame();
		One.displayTower();
		while (l) {
			k = false;
			One.userInput();
			i =  One.discPop();
			if (i) {
				j = One.discPush();
				new Tower().move();
			}
			One.displayTower();
			k = One.checkResult();
			if (k)
				l = One.result();
		}
	}
}
