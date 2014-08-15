#include<iostream>
#include<stack>
using namespace std;
class Hanoi
{
	stack<int> A, B, C;
	int sizeA, sizeB, sizeC, ActiveDisc, CurrentDisc, val[3], check;
	char PickUp, DropOut;
	static int count;
public:
	Hanoi()
	{
		for (int i = 3;i > 0 ;i--)
		{
			A.push(i);
			val[i-1] = i;
		}
		sizeA = A.size();
		check = 0;
	}
	static void move()
	{
		count++;
	}

	void dispA();
	void dispB();
	void dispC();
	void display();
	void input();
	int PerformPop();
	int PerformPush();
	int Check();
	int Result();
	~Hanoi(){}
};

int Hanoi::count=0;
  void Hanoi::dispA()
	{
		int a[3], i, j;
		j = A.size();
		if (!A.empty())
		{
			for (i = (A.size() - 1);i >= 0;i--)
			{
				a[i] = A.top();
				A.pop();
			}
			for (i = 0;i <= (j - 1);i++)
			{
				cout << a[i] << ' ';
				A.push(a[i]);
			}
			cout << endl;
		}
		else
			cout << endl;
	}
	void Hanoi::dispB()
	{
		int a[3], i, j;
		j = B.size();
		if (!B.empty())
		{
			for (i = (B.size() - 1);i >= 0 ;i--)
			{
				a[i] = B.top();
				B.pop();
			}
			for (i = 0 ;i <= (j - 1);i++)
			{
				cout << a[i] << ' ';
				B.push(a[i]);
			}
			cout << endl;
		}
		else
			cout << endl;
	}
	void Hanoi::dispC()
	{
		int a[3], i, j;
		j = C.size();
		if (!C.empty())
		{
			for (i = (C.size() - 1);i >= 0 ;i--)
			{
				a[i] = C.top();
				C.pop();
			}
			for (i = 0;i <= (j - 1) ;i++)
			{
				cout << a[i] << ' ';
				C.push(a[i]);
			}
			cout << endl;
		}
		else
			cout << endl;
	}
	void Hanoi::input()
	{
		cout << "Enter pole to pick and drop : ";
		cin >> PickUp >> DropOut;
	}
	int Hanoi::PerformPop()
	{
		int i = 0;
		if (PickUp != DropOut)
		{
			switch (PickUp)
			{
			case 'A':
				if (!A.empty())
				{
					ActiveDisc = A.top();
					A.pop();
					i = 1;
				}
				else
					cout << "\nError 402: Column Empty" << endl;
				break;
			case 'B':
				if (!B.empty())
				{
					ActiveDisc = B.top();
					B.pop();
					i = 1;
				}
				else
					cout << "\nError 402: Column Empty" << endl;
				break;
			case 'C':
				if (!C.empty())
				{
					ActiveDisc = C.top();
					C.pop();
					i = 1;
				}
				else
					cout << "\nError 402: Column Empty" << endl;
				break;
			default:
				cout << "\nError 404:Column not found" << endl;
			}
		}
		return i;
	}
	int Hanoi::PerformPush()
	{
		int i = 0;
		switch (DropOut)
		{
		case 'A' :
			if (!A.empty())
			{
				CurrentDisc = A.top();
				if (CurrentDisc > ActiveDisc)
				{
					A.push(ActiveDisc);
					i = 1;
				}
				else
				{
					if (PickUp == 'B')
						B.push(ActiveDisc);
					if (PickUp == 'C')
						C.push(ActiveDisc);
					cout << "\nError 401: Rule Break" << endl;
				}
			}
			else
				A.push(ActiveDisc);
			break;
		case 'B' :
			if (!B.empty())
			{
				CurrentDisc = B.top();
				if (CurrentDisc > ActiveDisc)
				{
					B.push(ActiveDisc);
					i = 1;
				}
				else
				{
					if (PickUp == 'B')
						B.push(ActiveDisc);
					if (PickUp == 'A')
						A.push(ActiveDisc);
					cout << "\nError 401: Rule Break" << endl;
				}
			}
			else
				B.push(ActiveDisc);
			break;
		case 'C' :
			if (!C.empty())
			{
				CurrentDisc = C.top();
				if (CurrentDisc > ActiveDisc)
				{
					C.push(ActiveDisc);
					i = 1;
				}
				else
				{
					if (PickUp == 'A')
						A.push(ActiveDisc);
					if (PickUp == 'C')
						C.push(ActiveDisc);
					cout << "\nError 401: Rule Break" << endl;
				}
			}
			else
				C.push(ActiveDisc);
			break;
		default:
			cout << "\nError 404:Column not found" << endl;
		}
		return i;
	}
	int Hanoi::Check()
	{
		int a[3], i, j;
		check = 0;
		j = C.size();
		if ((!C.empty()) && (j == 3))
		{
			for (i = (C.size() - 1);i >= 0 ;i--)
			{
				a[i] = C.top();
				C.pop();
			}
			for (i = 0;i <= (j - 1) ;i++)
			{
				C.push(a[i]);
			}
			for (i = 0;i <= 2;i++)
			{
				if (a[i] = val[i])
				{
					check++;
				}
			}
		}
		if (check == 3)
			return 1;
		else
			return 0;
	}
	int Hanoi::Result()
	{
		cout << "The game is over" << endl;
		cout << "Total moves :" << count <<endl;
		if (count <= 7)
		{
			cout << "AMAZING";
		}
		else if (count <= 13)
		{
			cout << "GOOD";
		}
		else
			cout << "NOT BAD";
		return 0;
	}
	void Hanoi::display()
	{
		cout << "A : ";
		dispA();
		cout << "B : ";
		dispB();
		cout << "C : ";
		dispC();
	}



