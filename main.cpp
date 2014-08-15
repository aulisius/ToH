#include"ToH.h"

int main()
{
	int i, j, k = 0, l = 1;
	Hanoi One;
	One.display();
	while (l)
	{
		k = 0;
		One.input();
		i =  One.PerformPop();
		if (i)
		{
			j = One.PerformPush();
			Hanoi::move();
		}
		One.display();
		k = One.Check();
		if (k == 1)
		{
			l = One.Result();
		}
	}
	return 0;
}
