#include<bits/stdc++.h>
using namespace std;
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
int main(){
	cin >> n;
	ktao();
	ok=1;
	int tmp=1;
	while(ok){
		cout << tmp++ <<": ";
		for(int i=1;i<=n;i++){
			cout << a[i] <<" ";
		}
		cout << endl;
		sinh();
	}
}
