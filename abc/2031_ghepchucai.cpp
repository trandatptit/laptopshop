#include<bits/stdc++.h>
using namespace std;
char c;
int n,k,ok,a[1000];
void ktao(){
	for(int i=1;i<=n;i++){
		a[i]=i;
	}
}
void sinh(){
	int i=n-1;
	while(i>=1&&a[i]>a[i+1]) i--;
	if(i==0) ok=0;
	else{
		int j=n;
		while(a[i]>a[j]) j--;
		swap(a[i],a[j]);
		sort(a+i+1,a+n+1);//dao nguoc day
	}
}
bool check(){
	for(int i=2;i<n;i++){
		if((a[i]==1 || a[i]==5) && (a[i-1]!=1 && a[i-1]!=5) && (a[i+1]!=1 && a[i+1]!=5)) return 0;
	}
	return 1;
}
int main(){
	cin >> c;
	n=int(c)-64;
	ktao();
	ok=1;
	while(ok){
		if(check()){
			for(int i=1;i<=n;i++){
				cout << char(a[i]+64);
			}
		cout << endl;
		}
		sinh();
	}
}
