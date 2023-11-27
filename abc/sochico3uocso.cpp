#include<bits/stdc++.h>
#define ll long long
using namespace std;

int snt(int n){
	if(n<2) return 0;
	for(int i=2;i<=sqrt(n);i++){
		if(n%i==0) return 0;
	}
	return 1;
}


int main(){
	int t;
	cin >> t;
	while(t--){
		ll l,r;
		cin >> l >> r;
		int dem=0;
		if(l<r){
			for(ll i=l;i*i<=r;i++){
				if(snt(i)) dem++;
			}
		}
		if(l>r) {
			for(ll i=r;i*i<=l;i++){
				if(snt(i)) dem++;
			}
		}
		cout << dem << endl;
	}
}
