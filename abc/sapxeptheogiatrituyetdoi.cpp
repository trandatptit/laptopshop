#include<bits/stdc++.h>
using namespace std;
int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin >> t;
	while(t--){
		int n,x;
		cin >> n >> x;
		int a[n];
		multimap<int,int> mp;
		for(int i=0;i<n;i++){
			cin >> a[i];
			mp.insert({abs(a[i]-x),a[i]});
		}
		for(auto y:mp){
			cout << y.second <<" ";
		}
		cout << endl;
	}
}

