#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >>t;
	while(t--){
		int n;
		cin >> n;
		int ans=0;
		int a[n],b[n-1];
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		for(int i=0;i<n-1;i++){
			cin >> b[i];
		}
		for(int i=0;i<n;i++){
			if(a[i]!=b[i]){
				ans=i+1;
				break;
			}
		}
		cout << ans << endl;
	}
}
