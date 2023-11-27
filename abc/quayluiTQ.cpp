#include<bits/stdc++.h>
using namespace std;

void quaylui(int i){
	int j;
	for(j=1;j<=n;j++){
		if(chon duoc j){
			xac dinh vi tri i theo j;
			if(i==n) in();
			else quaylui(i+1);
		}
	}
}

int main(){
	quaylui();
}
