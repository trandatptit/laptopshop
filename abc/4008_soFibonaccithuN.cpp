#include<bits/stdc++.h>
using namespace std;
int m=1e9+7;
long long f[1000];
long long fibonacci(int n){
	f[0]=0;
	f[1]=1;
	for(int i=2;i<=n;i++){
		f[i]=(f[i-1]%m+f[i-2]%m)%m;
	}
	return f[n];
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		cout << fibonacci(n) << endl;
	}
}
