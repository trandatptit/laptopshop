#include<bits/stdc++.h>
using namespace std;
int mod=1e9+7;

long long dao(long long k){
	long long tmp=0;
	while(k>0){
		tmp=(tmp*10+k%10);
		k=k/10;
	}
	return tmp;
}

long long powm(long long n,long long k){
	if(k==0) return 1;
	long long x=powm(n,k/2);
	if(k%2==0) return x*x%mod;
	return n*(x*x%mod)%mod; 
}
int main(){
	int t;
	cin >> t;
	while(t--){
		long long n;
		cin >> n;
		cout << powm(n,dao(n)) << endl;
	}
}
