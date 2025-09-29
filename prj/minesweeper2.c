#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>

int loc_x=1;
int loc_y=1;
int map[101][101]={};
int open[101][101]={};
int state=0;
int count;

void opening();
void starting();
void num();
void zero();
void game();
void show();
void update();
void takeinput();
void flag();
void restore();

int main()
{
	int setting;
	srand(time(NULL));
	system(" ");
	int size_x, size_y, mines;
	printf("Use 'wasd' to move cursor\nUse 'enter' to open\nUse 'del' to flag\nPlease pick a difficulty number from:\n1. Easy\n2. Medium\n3. Hard\n4. Custom\n");
	do{
		scanf("%d", &setting);
		if(setting<1||setting>4){
			printf("Difficulty not available. Please try again.\n");
		}
	}while(setting<1||setting>4);
	switch(setting){
		case 1:
		size_y=8;
		size_x=8;
		mines=10;
		break;
		case 2:
		size_y=16;
		size_x=16;
		mines=40;
		break;
		case 3:
		size_y=16;
		size_x=30;
		mines=99;
		break;
		case 4:
		printf("Please input number of columns, rows, and mines:\n");
		scanf("%d %d %d", &size_x, &size_y, &mines);
		break;
	}
	printf("\033[?25l");
	starting(size_x, size_y, mines);
	game(size_x, size_y);
	
	return 0;
}

void starting(int size_x, int size_y, int mines)
{
	printf("\033[2J\033[1;1H");
	int i, j;
	for(i=0;i<size_y;i++){
		for(j=0;j<size_x;j++){
			printf("%c%c", 219, 219);
		}
		printf("\n");
	}
	char ch=0;
	while(ch!=13&&ch!=32){
		printf("\033[0;34m\033[%d;%dH%c%c\033[0m", loc_y, (loc_x*2)-1, 219, 219);
		ch=getch();
		printf("\033[2D%c%c", 219, 219);
		switch(ch){
			case 'w':
			if(loc_y!=1){
				loc_y--;
			}
			break;
			case 's':
			if(loc_y!=size_y){
				loc_y++;
			}
			break;
			case 'a':
			if(loc_x!=1){
				loc_x--;
			}
			break;
			case 'd':
			if(loc_x!=size_x){
				loc_x++;
			}
			break;
			case 13:
			break;
			case 32:
			break;
		}
	}
	int k;
	for(k=0;k<mines;k++){
		int a, b;
		do{
			a=rand()%size_y+1;
			b=rand()%size_x+1;
		}while(a==loc_y||a==loc_y+1||a==loc_y-1||b==loc_x||b==loc_x-1||b==loc_x+1||map[a][b]==10);
		map[a][b]=10;
		num(a, b, size_x, size_y);
	}
//	show(size_x, size_y);
	count=(size_x*size_y)-mines;
	opening(loc_y, loc_x, size_x, size_y);
}

void num(int y, int x, int size_x, int size_y)
{
	if(x-1>=1){
		if(map[y][x-1]!=10)
		map[y][x-1]++;
		if(y-1>=1){
			if(map[y-1][x-1]!=10)
			map[y-1][x-1]++;
		}
		if(y+1<=size_y){	
			if(map[y+1][x-1]!=10)
			map[y+1][x-1]++;
		}
	}
	if(x+1<=size_x){
		if(map[y][x+1]!=10)
		map[y][x+1]++;
		if(y-1>=1){
			if(map[y-1][x+1]!=10)
			map[y-1][x+1]++;
		}
		if(y+1<=size_y){
			if(map[y+1][x+1]!=10)
			map[y+1][x+1]++;
		}
	}
	if(y-1>=1){
		if(map[y-1][x]!=10)
		map[y-1][x]++;
	}
	if(y+1<=size_y){
		if(map[y+1][x]!=10)
		map[y+1][x]++;
	}
}

void opening(int y, int x, int size_x, int size_y)
{
	if(open[y][x]==0){
		open[y][x]=1;
		if (map[y][x]==10){
			state=-1;
		}else if(map[y][x]!=0){
			printf("\033[%d;%dH%d \033[0m", y, (x*2)-1, map[y][x]);
		}else {
			printf("\033[%d;%dH  ", y, (x*2)-1);
			zero(y, x, size_x, size_y);
		}
		count--;
	}
	if(count==0&&state!=-1){
		state=1;
	}
}

void zero(int y, int x, int size_x, int size_y)
{
	if(x-1>=1){
		opening(y, x-1, size_x, size_y);
		if(y-1>=1){
			opening(y-1, x-1, size_x, size_y);
		}
		if(y+1<=size_y){
			opening(y+1, x-1, size_x, size_y);
		}
	}
	if(x+1<=size_x){
		opening(y, x+1, size_x, size_y);
		if(y-1>=1){
			opening(y-1, x+1, size_x, size_y);
		}
		if(y+1<=size_y){
			opening(y+1, x+1, size_x, size_y);
		}
	}
	if(y-1>=1){
		opening(y-1, x, size_x, size_y);
	}
	if(y+1<=size_y){
		opening(y+1, x, size_x, size_y);
	}
}

void game(int size_x, int size_y)
{
	int i, j;
	while(state==0){
		update();
		takeinput(size_x, size_y);
	}
	if(state==-1){
		printf("\033[2J\033[1;1H");
		for(i=1;i<=size_y;i++){
			for(j=1;j<=size_x;j++){
				if(map[i][j]!=10){
					if(open[i][j]==0){
						printf("%c%c", 219, 219);
					}else if(map[i][j]!=0){
						printf("%d ", map[i][j]);
					}else{
						printf("  ");
					}
				}else{
					printf("\033[0;31m%c%c\033[0m", 91, 93);
				}
			}
			printf("\n");
		}
		printf("\033[%d;1HGAME OVER", size_y+1);
	}else if(state==1){
		printf("\033[2J\033[1;1H");
		for(i=1;i<=size_y;i++){
			for(j=1;j<=size_x;j++){
				if(map[i][j]!=10){
					if(map[i][j]!=0){
						printf("%d ", map[i][j]);
					}else{
						printf("  ");
					}
				}else{
					printf("\033[0;91m%c%c\033[0m", 219, 219);
				}
			}
			printf("\n");
		}
		printf("\033[%d;1HYOU WIN!", size_y+1);
	}
	getch();
}

void show(int size_x, int size_y)
{
	printf("\033[15;1H");
	int i, j;
	for(i=1;i<=size_y;i++){
		for(j=1;j<=size_x;j++){
			printf("%d ", map[i][j]);
		}
		printf("\n");
	}
}

void update()
{
	printf("\033[?25l");
	if(open[loc_y][loc_x]==0){
		printf("\033[%d;%dH\033[0;34m%c%c\033[0m", loc_y, (loc_x*2)-1, 219, 219);
	}else if(open[loc_y][loc_x]==1){
		if(map[loc_y][loc_x]!=0){
			printf("\033[%d;%dH\033[44m%d \033[0m", loc_y, (loc_x*2)-1, map[loc_y][loc_x]);
		}else {
			printf("\033[%d;%dH\033[44m  \033[0m", loc_y, (loc_x*2)-1);
		}
	}else if(open[loc_y][loc_x]==2){
		printf("\033[%d;%dH\033[94m%c%c\033[0m", loc_y, (loc_x*2)-1, 219, 219);
	}
}

void takeinput(int size_x, int size_y)
{
	char ch=getch();
	restore();
	switch(ch){
		case 'w':
		if(loc_y>1){
			loc_y--;
		}
		break;
		case 's':
		if(loc_y<size_y){
			loc_y++;
		}
		break;
		case 'a':
		if(loc_x>1){
			loc_x--;
		}
		break;
		case 'd':
		if(loc_x<size_x){
			loc_x++;
		}
		break;
		case 13:
		opening(loc_y, loc_x, size_x, size_y);
		break;
		case 32:
		opening(loc_y, loc_x, size_x, size_y);
		break;
		case 8:
		flag(size_x, size_y);
		break;
	}
}

void flag(int size_x, int size_y)
{
	if(open[loc_y][loc_x]==0){
		open[loc_y][loc_x]=2;
	}else if(open[loc_y][loc_x]==2){
		open[loc_y][loc_x]=0;
	}
}

void restore()
{
	if(open[loc_y][loc_x]==0){
		printf("\033[%d;%dH%c%c", loc_y, (loc_x*2)-1, 219, 219);
	}else if(open[loc_y][loc_x]==1){
		if(map[loc_y][loc_x]!=0){
			printf("\033[%d;%dH%d ", loc_y, (loc_x*2)-1, map[loc_y][loc_x]);
		}else {
			printf("\033[%d;%dH  ", loc_y, (loc_x*2)-1);
		}
	}else if(open[loc_y][loc_x]==2){
		printf("\033[%d;%dH\033[91m%c%c\033[0m", loc_y, (loc_x*2)-1, 219, 219);
	}
}
