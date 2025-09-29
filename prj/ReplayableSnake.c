#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>
#include <time.h>

void box();
void pause();

int main()
{
	int replayable = 1;
	while(replayable){
		system("cls");
	int limit = 3;
	int direction = 1;
	int boxes[16][16]={0};
	int loc[2]={7, 3};
	int apple[2]={7, 12};
	char ch;
	system(" ");
	srand(time(NULL));
	printf("\033[?25l");
	boxes[loc[0]][loc[1]]++;
	printf("\033[%d;%dH%c%c", loc[0]+2, (loc[1]+1)*2, 219, 219);
	printf("\033[0;31m\033[%d;%dH%c%c\033[0m", apple[0]+2, (apple[1]+1)*2, 219, 219);
	box();
	while(!_kbhit()){
		
	}
	while(1){
		if(kbhit()){
			ch=getch();
			switch(ch){
				case 'w':
				if(direction!=2){
					direction=4;
				}
				break;
				case 's':
				if(direction!=4){
					direction=2;
				}
				break;
				case 'd':
				if(direction!=3){
					direction=1;
				}
				break;
				case 'a':
				if(direction!=1){
					direction=3;
				}
				break;
				case 27:
				pause();
				break;
			}
		}
		switch(direction){
			case 1:
			loc[1]++;
			break;
			case 2:
			loc[0]++;
			break;
			case 3:
			loc[1]--;
			break;
			case 4:
			loc[0]--;
			break;
		}
		int i, j;
		for(i=0;i<16;i++){
			for(j=0;j<16;j++){
				if(boxes[i][j]!=0){
					boxes[i][j]++;
					if(boxes[i][j]>limit){
						boxes[i][j]=0;
						printf("\033[%d;%dH  ", i+2, (j+1)*2);
					}
				}
			}
		}
		boxes[loc[0]][loc[1]]++;
		printf("\033[%d;%dH%c%c", loc[0]+2, (loc[1]+1)*2, 219, 219);
		if(loc[0]>15||loc[0]<0||loc[1]>15||loc[1]<0||boxes[loc[0]][loc[1]]>1){
			break;
		}
//		if(limit==256){
//			break;
//		}
		usleep(125000);
		if(loc[0]==apple[0]&&loc[1]==apple[1]){
			limit++;
//			printf("\a");
			int a, b;
			do{
				a=rand()%16;
				b=rand()%16;
			}while(boxes[a][b]!=0);
			apple[0]=a;
			apple[1]=b;
		}
		printf("\033[0;31m\033[%d;%dH%c%c\033[0m", apple[0]+2, (apple[1]+1)*2, 219, 219);
	}
	if(limit<256){
		printf("\033[20;1HCrash!\nYour score is : %d", limit-3);
	}else {
		printf("\033[20;1HYou won!");
	}
	printf("\n\nPress enter to play again...");
	char playAgain = 0;
	while(playAgain != 13 && playAgain != 32){
		playAgain = getch();
		if(playAgain == 8 || playAgain == 27){
			replayable = 0;
			break;
		}
	}
	
	}
	return 0;
}

void box()
{
	int i;
	printf("\033[1;1H%c", 201);
	for(i=0;i<32;i++){
		printf("%c", 205);
	}
	printf("%c", 187);
	for(i=0;i<16;i++){
		printf("\033[%d;1H%c\033[32C%c", 2+i, 186, 186);
	}
	printf("\033[18;1H%c", 200);
	for(i=0;i<32;i++){
		printf("%c", 205);
	}
	printf("%c", 188);
}

void pause()
{
	char ch=0;
	while(1){
		ch=getch();
		if(ch==27){
			printf("\033[20;1H");
			exit(0);
		}else if(ch==13){
			break;
		}
	}
}
