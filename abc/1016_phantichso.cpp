#include<bits/stdc++.h>
using namespace std;
int n,a[100];
void in(int i){
	cout << "[";
	for(int k=1;k<i;k++){
		cout << a[k]<<" ";
	}
	cout << a[i] <<"]";
}
void quaylui(int n,int i,int s){
	for(int j=n;j>=1;j--){
		a[i]=j;
		if(j==s) in(i);
		else if(j<s) quaylui(j,i+1,s-j);
	}
}

int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		quaylui(n,1,n);
		cout << endl;
	}
}
