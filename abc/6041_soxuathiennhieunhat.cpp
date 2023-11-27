#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		pair<int,int> s;
		map<int,int> m;
		for(int i=0;i<n;i++){
			cin >> a[i];
			m[a[i]]++;
		}
		int tmp=0;
		for(auto x:m){
			if(x.second>tmp){
				tmp=x.second;
				s=x;
			}
		}
		if(s.second>(int)n/2){
			cout << s.first << endl;
		}
		else cout << "NO" <<endl;
	}
}
