#include<bits/stdc++.h>
using namespace std;
int n,a[15],b[15]={0};
void in(){
	for(int i=1;i<=n;i++){
		cout << a[i] << " ";
	}
	cout << endl;
}

void quaylui(int i){
	int j;
	for(j=1;j<=n;j++){
		if(!b[j]){
			a[i]=j; b[j]=1;
			if(i==n) in();
			else quaylui(i+1);
			b[j]=0;
		}
	}
}

int main(){
	cin >> n;
	quaylui(1);
}
