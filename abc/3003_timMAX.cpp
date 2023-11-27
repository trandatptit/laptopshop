#include<bits/stdc++.h>
using namespace std;

int a[1000005];
int mod=1e9+7;

int main(){
	int t;
	cin >> t;
	while(t--){
		memset(a,0,sizeof(a));
		int n;
		cin >> n;
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		sort(a,a+n);
		long long res=0;
		for(int i=1;i<n;i++){
			res += i*a[i]%mod;
			res %= mod;
		}
		cout << res << endl;
	}
}
