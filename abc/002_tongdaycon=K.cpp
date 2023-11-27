#include<bits/stdc++.h>
using namespace std;
int n,k,ok,a[30],b[30];
void ktao(){
	for(int i=1;i<=n;i++){
		a[i]=0;
	}
}
void sinh(){
	int i=n;
	while(i>=1 && a[i]==1){
		a[i]=0;
		i--;
	}
	if(i==0) ok=0;
	else a[i]=1;
}
bool check(){
	int tmp=0;
	for(int i=1;i<=n;i++){
		if(a[i]==1){
			tmp+=b[i];
		}
	}
	if(tmp==k) return 1;
	return 0;
}
int main(){
	cin >>n >>k ;
	for(int i=1;i<=n;i++){
		cin >> b[i];
	}
	ok=1;
	ktao();
	int dem=0;
	while(ok){
		
		if(check()){
			dem++;
			for(int i=1;i<=n;i++){
				if(a[i]==1) cout << b[i] <<" ";
			}
			cout << endl;
		}
		sinh();
	}
	cout << dem ;
}
