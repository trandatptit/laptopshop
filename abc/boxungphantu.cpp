#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		int dem=0;
		int cnt[100001]={0};
		for(int i=0;i<n;i++){
			cin >> a[i];
			cnt[a[i]]++;
		}
		sort(a,a+n);
		for(int i=a[0];i<a[n-1];i++){
			if(cnt[i]==0){
				dem++;
			}
		}
		cout << dem << endl;
	}
}
