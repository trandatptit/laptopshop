#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n,m;
		cin >> n >> m;
		int a[n],b[m];
		int cnt[100000]={0};
		for(int i=0;i<n;i++){
			cin >> a[i];
			cnt[a[i]]++;
		}
		for(int i=0;i<m;i++){
			cin >> b[i];
			cnt[b[i]]++;
		}
		for(int i=0;i<n+m;i++){
			if(cnt[i]>0){
				cout << i <<" ";
			}
		}
		cout << endl;
		for(int i=0;i<n+m;i++){
			if(cnt[i]>1){
				cout << i <<" ";
			}
		}
		cout << endl;
	}
}
