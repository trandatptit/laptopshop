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
		for(int i=0;i<n-k+1;i++){
			int tmp=i;
			int max=a[i];
			for(int j=i+1;j<=i+k-1;j++){
				if(a[tmp]<a[j]){
					tmp=j;
				}
			}
			max=a[tmp];
			cout << max <<" ";
		}
		cout << endl;
	}
}
