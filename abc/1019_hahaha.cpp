#include<bits/stdc++.h>
using namespace std;
int n,ok,a[30];
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
bool check1(){
	if(a[1]==1 && a[n]==0) return 1;
	return 0;
}
bool check2(){
	for(int i=1;i<=n;i++){
		if(a[i]==1 && a[i+1]==1) return 0;
	}
	return 1;
}
int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		ktao();
		ok=1;
		while(ok){
			if(check1() && check2()){
				for(int i=1;i<=n;i++){
					if(a[i]==0){
						cout << char(a[i]+65);
					}
					else{
						cout << char(a[i]+71);
					}
				}
				cout << endl;
			}
			sinh();
		}
	}
}
