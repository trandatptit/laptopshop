#include<bits/stdc++.h>
#define ll long long
using namespace std;
int mod=1e9+7;
struct MaTran{
	ll x[2][2];
	
};
MaTran operator * ( MaTran a, MaTran b){
	MaTran c;
	for(int i=0;i<2;i++){
		for(int j=0;j<2;j++){
			c.x[i][j]=0;
			for(int k=0;k<2;k++){
				ll tmp=(a.x[i][k]*b.x[k][j])%mod;
				c.x[i][j] += tmp;
				c.x[i][j] %=mod;
			}
		}
	}
	return c;
}
MaTran powmod(MaTran a, int n){
	if(n==1) return  a;
	MaTran X=powmod(a, n/2);
	if(n%2==0) return X*X;
	else return a*X*X;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		MaTran A,res;
		A.x[0][0]=1;
		A.x[0][1]=1;
		A.x[1][0]=1;
		A.x[1][1]=0;
		res=powmod(A,n-1);
		cout << res.x[0][0] << endl;
	}
}
