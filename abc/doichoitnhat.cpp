#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		int cnt=0;
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		for(int i=0;i<n-1;i++){
			int min=i;
			for(int j=i+1;j<n;j++){
				if(a[min]>a[j]){
					min=j;
				}
			}
			if(min!=i) ++cnt;
			swap(a[min],a[i]);
			
		}
		cout << cnt << endl;
	}
}
