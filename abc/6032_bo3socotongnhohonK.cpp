#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n, k;
		cin >> n >> k;
		int a[n];
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		sort(a,a+n);
		int tmp=0;
		
		for(int i=0;i<=n-2;i++){
			for(int j=i+2;j<n-1;j++){
				if((a[i]+a[j]+a[j+1])<k)
			}
		}
		cout << tmp << endl;
	}
}
