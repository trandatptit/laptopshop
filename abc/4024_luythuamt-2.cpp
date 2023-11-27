#include<bits/stdc++.h>
using namespace std;
int mod = 1e9+7;
int n;
int k;
long long F[10][10], M[10][10];
void Mul(long long x[10][10], long long y[10][10]){
	long long v[10][10];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			long long res=0;;
			for(int tmp=0;tmp<n;tmp++){
				res += (x[i][tmp] * y[tmp][j])%mod;
				res = res%mod;
			}
			v[i][j]=res;
		}
	}
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			F[i][j]=v[i][j];
		}
	}
} 
void Pow(long long f[10][10],int k){
	if(k<=1) return;
	Pow(f,k/2);
	Mul(f,f);
	if(k%2==1) Mul(f,M);
}


int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n >> k;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				cin >> F[i][j];
				M[i][j] = F[i][j];
			}
		}
		Pow(F,k);
		long long ans=0;
		for(int i=0;i<n;i++){
			ans += F[i][n-1];
			ans %= mod;
		}
		cout << ans << endl;
	}
}
