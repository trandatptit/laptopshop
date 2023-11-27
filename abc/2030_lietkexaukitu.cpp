#include<bits/stdc++.h>
using namespace std;
char x;
int n,k,a[30];

void inkq(){
	for(int i=1;i<=k;i++){
		cout << char(a[i]+64);
	}
	cout << endl;
}

void Try(int i){
	for(int j=a[i-1];j<=n;j++){ 
		a[i]=j;
		if(i==k) inkq();
		else Try(i+1);
	}
}

int main(){
	cin >> x >> k;
	n=int(x)-64;
	a[0]=1;
	Try(1);
}
