#include<bits/stdc++.h>
#define ll long long
using namespace std;

int main(){

	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		ll a[n];
		string tmp="";
		set<char> s;
		for(ll i=0;i<n;i++ ){
			cin >> a[i];
			tmp+=to_string(a[i]);
		}
		for(int i=0;i<tmp.size();i++){
			s.insert(tmp[i]);
		}
		for(auto x:s){
			cout << x << " ";
		}
		cout << endl;
	}
}
