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
		int sum=0, res=-1e9;
		for(int i=0;i<n;i++){
			sum+=a[i];
			res=max(sum,res);
			if(sum<0) sum=0;
		}
		cout << res << endl;
	}
}
