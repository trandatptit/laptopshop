#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n,k;
		int cnt=0;
		cin >>n >>k;
		int a[k];
		set<int> s,v;
		for(int i=1;i<=k;i++){
			cin >> a[i];
			s.insert(a[i]);
		}
		int ok=0;
		int i=k;
		while(i>=1 && a[i]==n-k+i) i--;
		if(i==0) {
			cnt=k;
		}
		else{
			a[i]++;
			for(int j=i+1;j<=k;j++){
				a[j]=a[j-1]+1;
			}
		}
		for(int i=1;i<=k;i++){
			v.insert(a[i]);
		}
		for(auto x:s){
			if(v.count(x) == 0){
				cnt++;
			}
		}
		cout << cnt<< endl;
	}
}

