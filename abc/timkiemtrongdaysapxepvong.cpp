#include<bits/stdc++.h>
using namespace std;


int main(){
	int t;
	cin >> t;
	while(t--){
		int n,x;
		cin >> n >> x;
		int a[n];
		int tmp;
		for(int i=0;i<n;i++){
			cin >> a[i];
			if(a[i]==x){
				tmp=i;
			}
		}
		cout <<tmp+1;
		cout << endl;
	}
}
