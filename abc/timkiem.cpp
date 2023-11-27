#include<bits/stdc++.h>
using namespace std;


int main(){
	int t;
	cin >> t;
	while(t--){
		int n,k;
		cin >> n>>k;
		set<int> s;
		for(int i=0;i<n;i++){
			int x;
			cin >> x;
			s.insert(x);
		}
		if(s.find(k)!=s.end()) cout << "1";
		else cout << "-1";
		cout << endl;
	}
	
}
