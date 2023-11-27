#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		sort(a,a+n);
		int tmp=1e9;
		for(int i=0;i<n;i++){
			auto it=lower_bound(a+i+1,a+n,a[i]);
			if(it!=a+n){
				tmp=min(tmp,*it-a[i]);
			}
		}
		cout << tmp << endl;
	}
}
