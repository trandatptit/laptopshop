#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		for(int i=1;i<=n;i++){
			cin >> a[i];
		}
		for(int i=n-1;i>=1;i--){
			if(a[i]<a[i+1]){
				int j=n;
				while(a[i]>a[j]){
					j--;
				}
				swap(a[i],a[j]);
				sort(a+i+1,a+n+1);
			}
			else if( a[i]>a[i+1]){
				sort(a+1,a+n+1);
			}
			break;
		}
		for(int i=1;i<=n;i++){
			cout << a[i] <<" ";
		}
		cout << endl;
	}
}
