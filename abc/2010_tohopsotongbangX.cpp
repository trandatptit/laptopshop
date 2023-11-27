#include<bits/stdc++.h>
using namespace std;
int n,a[100],c[100],x,s,ok;

void quaylui(int i,int s){
	int j;
	for(int j=1;j<=n;j++){
		if(a[j] >= c[i-1] && s+a[j]<=x){
			c[i]=a[j];
			s=s+a[j];
			if(s==x){
				ok=1;
				cout << "[";
				for(int k=1;k<i;k++){
					cout << c[k] <<" ";
				}
				cout << c[i] <<"]";
			}
			else quaylui(i+1,s);
			s=s-a[j];
		}
	}
}
int main(){
	int t;
	cin >> t;
	while(t--){
		ok=0;
		cin >>n >>x;
		for(int i=1;i<=n;i++){
			cin >> a[i];
		}
		quaylui(1,0);
		if(ok==0) cout << "-1";
		cout << endl;
	}
	
}
