#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >>t;
	while(t--){
		int n;
		cin >> n;
		int a[n+1];
		pair<int,int> k;
		k.second=1e9;
		map<int,int> m;
		for(int i=1;i<=n;i++){
			cin >> a[i];
			if(m[a[i]]==0){
				m[a[i]]=i;
			}
			else if(k.second>m[a[i]]){
				k.first=a[i];
				k.second=m[a[i]];
			}
		}
		if(k.second!=1e9){
			cout << k.first << endl;
		}
		else{
			cout << "NO" << endl;
		}
	}
}
