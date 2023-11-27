#include<bits/stdc++.h>
using namespace std;

int f[1005],n,m,a[1005],c[1005];
int qhd(int a[],int n, int s){
	int f[s+1]={0},i,j;
	f[0]=1;
	for(int i=1;i<=n;i++){
		for(int j=s;j>=a[i];j--){
			if(f[j]==0 && f[j-a[i]]==1)
				f[j]=1;
		}
	}
	return f[s];
}
int main(){
	int t;
	cin >> t;
	while(t--){
		int n,s,t;
		cin >> n >> s;
		int a[n];
		for(int i=1;i<=n;i++){
			cin >> a[i];
		}
		int tmp=qhd(a,n,s);
		if(tmp==1) cout << "YES" << endl;
		else cout << "NO" << endl;
	}
	
}
