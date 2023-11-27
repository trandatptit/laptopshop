#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n,k;
		cin >> n >> k;
		int a[n];
		for(int i=0;i<n;i++){
			cin >> a[i];
		}n
		sort(a,a+n);
		long long cnt=0;
		for(int i=0;i<n-1;i++){
			auto it1=lower_bound(a+i+1,a+n,k-a[i]);
			auto it2=upper_bound(a+i+1,a+n,k-a[i]);
			if(*it1==k-a[i]) cnt+=it2-it1;
		}
		cout << cnt <<endl;
	}
}

