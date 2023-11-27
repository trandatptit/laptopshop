#include<bits/stdc++.h>
using namespace std;
long long mod = 123456789;
long long Pow(int a,long long n){
	if(n == 1) return a;
	long long x = Pow(a,n/2);
	if(n%2==0) return (x*x)%mod;
	else return (a*((x*x)%mod))%mod;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		long long n;
		cin >> n;
		cout << Pow(2,n-1) << endl;
	}
}
