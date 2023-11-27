#include<bits/stdc++.h>
#define ll long long
using namespace std;
int prime[10000000];
void sang(ll l,ll r){
	
	for(int i=0;i<=r-l+1;i++){
		prime[i]=1;
	}
	for(long long i=2;i<=sqrt(r);i++){
		for(long long j=max(i*i,(l+i-1)/i*i);j<=r;j+=i){
			prime[j-l]=0;
		}
	}
}

bool tang(long long n){
	string s=to_string(n);
	for(int i=0;i<s.size()-1;i++){
		if((s[i]-'0')>=(s[i+1]-'0')) return 0;
	}
	return 1;
}
bool giam(long long n){
	string s=to_string(n);
	for(int i=0;i<s.size()-1;i++){
		if((s[i]-'0')<=(s[i+1]-'0')) return 0;
	}
	return 1;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		int dem=0;
		cin >> n;
		ll l=pow(10,n-1);
		ll r=pow(10,n)-1;
		sang(l,r);
		for(long long i=l;i<=r;i++){
			if(prime[i-l]){
				if(tang(i) || giam(i)) dem++;
			}
		}
		cout << dem << endl;
	}
}
