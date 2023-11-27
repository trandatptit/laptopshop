#include<bits/stdc++.h>
using namespace std;
int n,b[30];
void in(){
	for(int i=1;i<=n;i++){
		cout << b[i] << " ";
	}
	cout << endl;
}

void quaylui(int i){
	int j;
	for(j=0;j<=1;j++){
		b[i]=j;
		if(i==n) in();
		else quaylui(i+1);
	}
}

int main(){
	cin >> n;
	quaylui(1);
}
