#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		long long k;
		cin >> n>> k;
		vector<long long> a(n);
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		long long it=lower_bound(a.begin(),a.end()-1,k)-a.begin();
		if(a[it]!=k) it-=1;
		if(it!=-1) it+=1;
		cout << it << endl;
	}
}
