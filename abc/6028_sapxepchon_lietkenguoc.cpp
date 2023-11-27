#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin >>n;
	int a[n];
	vector<int> res[n];
	for(int i=0;i<n;i++){
		cin >> a[i];
	}
	for(int i=0;i<n-1;i++){
		int k=i;
		for(int j=i+1;j<n;j++){
			if(a[k]>a[j]){
				k=j;
			}
		}
		swap(a[i],a[k]);
		for(int j=0;j<n;j++){
			res[i].push_back(a[j]);
		}
	}
	for(int i=n-2;i>=0;i--){
		cout << "Buoc " <<i+1 << ": ";
		for(auto x : res[i]){
			cout << x << " ";
		}
		cout << endl;
	}
}
