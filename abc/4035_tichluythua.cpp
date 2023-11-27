#include<bits/stdc++.h>
using namespace std;
int m=1e9+7;

long long powM(int a, long long b){
	if(b==0) return 1;
	long long x=powM(a,b/2);
	if(b%2==0) return x*x%m;
	else return a*(x*x%m)%m;
}
int main(){
	while(1){
		long long a,b;
		cin >> a >> b;
		if(a==0 && b==0) break;
		else cout << powM(a,b) << endl;
	}
}
