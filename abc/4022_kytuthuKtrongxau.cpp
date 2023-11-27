#include<bits/stdc++.h>
using namespace std;

long long tinh(int n,int k){
	int x=pow(2,n-1);
	if(k==x) return n;
	else if(k<x) return tinh(n-1,k);
	else return tinh(n-1,k-x);
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n,k;
		cin >>n >>k;
		string s;
		s='A'+tinh(n,k)-1;
		cout << s << endl;
	}
}
