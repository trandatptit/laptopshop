#include<bits/stdc++.h>
using namespace std;
void Try(int n,int a[] ){
	if(n>0){
		cout << "[";
		for(int i=0;i<n-1;i++){
			cout << a[i] <<" ";
		}
		cout << a[n-1] << "]";
		cout << endl;
		for(int i=0;i<n;i++){
			a[i]=a[i]+a[i+1];
		}
		Try(n-1,a);
	}
}
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
		Try(n,a);
	}
}

