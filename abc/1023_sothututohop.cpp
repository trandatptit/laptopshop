#include<bits/stdc++.h>
using namespace std;
int n,k,ok,a[15],b[15];
void ktao(){
	for(int i=1;i<=k;i++){
		a[i]=i;
	}
}
void sinh(){
	int i=k;
	while(i>=1 && a[i]==n-k+i) i--;
	if(i==0) ok=0;
	else {
		a[i]++;
		for(int j=i+1;j<=k;j++){
			a[j]=a[j-1]+1;
		}
	}
}
bool check(){
	for(int i=1;i<=k;i++){
		if(a[i]!=b[i]) return 1;
	}
	return 0;
}
int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n >> k;
		for(int i=1;i<=k;i++){
			cin >> b[i];
		}
		int cnt=1;
		ok=1;
		ktao();
		while(ok){
			if(check()) cnt++;
			else break;
			sinh();
		}
		cout << cnt << endl;
	}
}
