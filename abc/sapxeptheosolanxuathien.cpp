#include<bits/stdc++.h>
using namespace std;

bool cmp(pair<int,int> a, pair<int,int> b){
	if(a.second>b.second){
		return 1;
	}
	if(a.second==b.second && a.first<b.first){
		return 1;
	}
	return 0;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		unordered_map<int,int> m;
		vector<pair<int,int>> v;
		for(int i=0;i<n;i++){
			cin >> a[i];
			m[a[i]]++;
		}
		for(auto &x:m){
			v.push_back({x.first, x.second});
		}
		sort(v.begin(),v.end(),cmp);
		for(auto &y:v){
			for(int i=0;i<y.second;i++){
				cout << y.first<<" ";
			}
		}
		cout << endl;
	}
}
