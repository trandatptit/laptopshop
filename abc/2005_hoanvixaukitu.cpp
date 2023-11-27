#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		string s;
		cin >>s;
		vector<char> v;
		map<char,int> mp;
		for(int i=0;i<s.size();i++){
			mp[s[i]]++;
		}
		for(auto i: mp){
			v.push_back(i.first);
		}
		int n=v.size();
		int a[n+1];
		for(int i=1;i<=n;i++){
			a[i]=i;
		}
		int ok=1;
		while(ok){
			for(int i=1;i<=n;i++){
				cout << v[a[i]-1];
			}
			cout << " ";
			int i=n-1;
			while(i>=1 && a[i]>a[i+1]) i--;
			if(i==0) ok=0;
			else {
				int j=n;
				while(a[j]<a[i]) j--;
				swap(a[i],a[j]);
				sort(a+i+1,a+n+1);
			}
		}
		cout << endl;
	}
}
