#include<bits/stdc++.h>
#define ll long long
using namespace std;

ll mod=1e9+7;
ll n;
ll  F[2][2], M[2][2];
void nhanmt(ll f[2][2], ll m[2][2]){
	ll x = (f[0][0]*m[0][0]%mod + f[0][1]*m[1][0]%mod)%mod;
	ll y = (f[0][0]*m[0][1]%mod + f[0][1]*m[1][1]%mod)%mod;
	ll z = (f[1][0]*m[0][0]%mod + f[1][1]*m[1][0]%mod)%mod;
	ll t = (f[1][0]*m[0][1]%mod + f[1][1]*m[1][1]%mod)%mod;
	F[0][0] = x; F[0][1] = y;
	F[1][0] = z; F[1][1] = t;
	
}

void powmt(ll f[2][2], ll n){
	if(n<=1) return;
	powmt(f,n/2);
	nhanmt(f,f);
	if(n%2==1) nhanmt(f,M);
}
void solve(){
	F[0][0]=F[0][1]=F[1][0] = 1;
	F[1][1] = 0;
	M[0][0]=M[0][1]=M[1][0] = 1;
	M[1][1] = 0;
	cin >> n;
	if(n==0) cout << 0;
	else{
		powmt(F,n-1);
		cout << F[0][0];
	}
	cout << endl;
}
int main(){
	int t;
	cin >> t;
	while(t--){
		solve();
	}
}
