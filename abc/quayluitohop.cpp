#include<bits/stdc++.h>
using namespace std;
int n,k,b[30],c[30];
void in(){
	for(int i=1;i<=k;i++){
		cout << c[b[i]] << " ";
	}
	cout << endl;
}
bool check(){
	for(int i=1;i<k;i++){
		if(c[b[i]] >= c[b[i+1]]) return 0;
	}
	return 1;
}
void quaylui(int i){
	int j;
	for(j=b[i-1]+1;j<=n-k+i;j++){
		b[i]=j;
		if(i==k) {
			if(check()) in();
		}
		else quaylui(i+1);
	}
}

int main(){
	cin >> n >>k;
	for(int i=1;i<=n;i++){
		cin >> c[i];
	}
	quaylui(1);
}
