#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n,k;
		cin >>n >>k;
		int a[k];
		for(int i=1;i<=k;i++){
			cin >> a[i];
		}
		int ok=0;
		int i=k;
		while(i>=1 && a[i]==n-k+i) i--;
		if(i==0) {
			a[i]=0;
			for(int j=i+1;j<=k;j++){
				a[j]=a[j-1]+1;
			}
		}
		else{
			a[i]++;
			for(int j=i+1;j<=k;j++){
				a[j]=a[j-1]+1;
			}
		}
		for(int i=1;i<=k;i++){
			cout << a[i] << " ";
		}
		cout << endl;
	}
}

