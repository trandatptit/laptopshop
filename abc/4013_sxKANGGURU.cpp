#include<bits/stdc++.h>
using namespace std;

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		sort(a, a+n);
		int res=n;
		for(int i=n/2-1, j=n-1; i>=0 && j>=n/2;){
			if(a[j]>=a[i]*2){
				res--;
				i--; j--;
			}
			else i--;
		}
		cout << res << endl;
	}
}
