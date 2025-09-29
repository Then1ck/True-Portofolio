#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct Tile {
	bool up, down, left, right, altered;
}Map[101][101];

struct Movement {
	int X, Y;
	struct Movement *next, *prev;
}*head=NULL, *tail=NULL;

struct Movement *CreateMovement(int x, int y){
	struct Movement *New = (struct Movement*)malloc(sizeof(struct Movement));
	New -> X = x;
	New -> Y = y;
	New -> next = NULL;
	New -> prev = NULL;
	return New;
}

bool CheckEmpty(int x, int y, int maxX, int maxY){
	if(x<0||x>=maxX||y<0||y>=maxX)return 0;
	if(Map[y][x].altered)return 0;
	return 1;
}

bool Pathless(int x, int y, int maxX, int maxY){
	if(CheckEmpty(x, y+1, maxX, maxY))return 0;
	if(CheckEmpty(x, y-1, maxX, maxY))return 0;
	if(CheckEmpty(x+1, y, maxX, maxY))return 0;
	if(CheckEmpty(x-1, y, maxX, maxY))return 0;
	return 1;
}

void Walk(int x, int y){
	int i, j;
	while(1){
		if(head==NULL){
			head = tail = CreateMovement(0, 0);
		}
//		printf("%d %d\n", tail -> X, tail -> Y);
		if(Pathless(tail -> X, tail -> Y, x, y)){
			if(!Map[tail -> Y][tail -> X].altered)Map[tail -> Y][tail -> X].altered = true;
			if(head == tail){
				head = tail = NULL;
				return;
			}
			tail -> prev -> next = NULL;
			tail = tail -> prev;
			return;
		}
		int tempx, tempy, roll;
		do{
			roll = rand()%4;
			switch(roll){
				case 0:
					tempx = tail -> X;
					tempy = tail -> Y - 1;
				break;
				case 1:
					tempx = tail -> X + 1;
					tempy = tail -> Y;
				break;
				case 2:
					tempx = tail -> X;
					tempy = tail -> Y + 1;
				break;
				case 3:
					tempx = tail -> X - 1;
					tempy = tail -> Y;
				break;
			}
		}while(!CheckEmpty(tempx, tempy, x, y));
		struct Movement *New;
		switch(roll){
			case 0:
				Map[tail -> Y][tail -> X].up = false;
				Map[tail -> Y - 1][tail -> X].down = false;
				New = CreateMovement(tail -> X, tail -> Y - 1);
			break;
			case 1:
				Map[tail -> Y][tail -> X].right = false;
				Map[tail -> Y][tail -> X + 1].left = false;
				New = CreateMovement(tail -> X + 1, tail -> Y);
			break;
			case 2:
				Map[tail -> Y][tail -> X].down = false;
				Map[tail -> Y + 1][tail -> X].up = false;
				New = CreateMovement(tail -> X, tail -> Y + 1);
			break;
			case 3:
				Map[tail -> Y][tail -> X].left = false;
				Map[tail -> Y][tail -> X - 1].right = false;
				New = CreateMovement(tail -> X - 1, tail -> Y);
			break;
		}
		Map[tail -> Y][tail -> X].altered = true;
		tail -> next = New;
		New -> prev = tail;
		tail = New;
		/*
		for(i=0;i<y;i++){
			for(j=0;j<x;j++){
				printf("%d ", Map[i][j].altered);
			}
			printf("\n");
		}
		printf("\n");
		*/
		Walk(x, y);
	}
}

void DrawMap(int x, int y){
	int i, j;
	for(i=0;i<4+(y-1)*3;i++){
		for(j=0;j<(4+(y-1)*3)*2;j++){
			if(i%3==0&&(j/2)%3==0)printf("%c", 219);
			else if(i%3!=0&&(j/2)%3!=0)printf(" ");
			else if(i==0||j==0||j==1||i==3+(y-1)*3||j==((3+(y-1)*3)*2)+1||j==(3+(y-1)*3)*2)printf("%c", 219);
			else if(i%3==0){
				if(Map[i/3][j/6].up)printf("%c", 219);
				else printf(" ");
			}else {
				if(Map[i/3][j/6].left)printf("%c", 219);
				else printf(" ");
			}
		}
		printf("\n");
	}
}

int main()
{
	system("mode con: cols=120 lines=50");
	srand(time(NULL));
	int size, i, j;
	while(1){
		printf("Input size: ");
		scanf("%d", &size);
		if(size > 15)printf("Size might not fit within the window...\n");
		else break;
	}
	system("cls");
	for(i=0;i<size;i++){
		for(j=0;j<size;j++){
			Map[i][j].up = true;
			Map[i][j].down = true;
			Map[i][j].left = true;
			Map[i][j].right = true;
			Map[i][j].altered = false;
		}
	}
	Walk(size, size);
	/*
	for(i=0;i<y;i++){
		for(j=0;j<x;j++){
			printf("|%d %d %d %d| ", Map[i][j].up, Map[i][j].right, Map[i][j].down, Map[i][j].left);
		}
		printf("\n");
	}
	*/
	DrawMap(size, size);
	getch();
	
	return 0;
}
