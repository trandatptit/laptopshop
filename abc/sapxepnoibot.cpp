#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin >> n;
	int a[n];
	for(int i=0;i<n;i++){
		cin >> a[i];
	}
	int tmp=1;
	for(int i=0;i<n-1;i++){
		int check=false;
		for(int j=0;j<n-i-1;j++){
			if(a[j+1]<a[j]){
				check=true;
				swap(a[j+1],a[j]);
			}
		}
		if(!check){
			break;
		}
		cout << "Buoc " << tmp++ <<": ";
		for(int t=0;t<n;t++){
			cout << a[t]<<" ";
		}
		cout << endl;
	}
}
