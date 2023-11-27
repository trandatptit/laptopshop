#include<bits/stdc++.h>
using namespace std;
int n;long long k, f[93];
long long tinh(int n,long long k){
	if(n<2) return n;
	if(k<=f[n]) return tinh(n-1,k);
	return f[n-1]+tinh(n-2,k-f[n]);
}
int main(){
	int t;
	cin >> t;
	f[0]=0; f[1]=1;
	for(int i=2;i<=92;i++) f[i]=f[i-2]+f[i-1];
	while(t--){
		cin >> n >>k;
		cout << tinh(n,k) <<endl;
	}
}
