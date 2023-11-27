#include<bits/stdc++.h>
using namespace std;
int n,ok,a[30];

void ktao(){
	for(int i=1;i<=n;i++) {
		a[i]=6;
	}
}

void sinh(){
	int i=n;
	while(i>=1 && a[i]==8){
		a[i]=6;
		i--;
	}
	if(i==0){
		ok=0;
	}
	else {
		a[i]=8;
	}
}

bool check1(){
	if(a[1]==8 && a[n]==6) return 1;
	return 0;
}
bool check2(){
	for(int i=1;i<n;i++){
		if(a[i]==8 && a[i+1]==8) return 0;
	}
	return 1;
}
bool check3(){
	for(int i=1;i<=n;i++){
		if(a[i]==6 && a[i+1]==6 &&a[i+2]==6 && a[i+3]==6) return 0;
	}
	return 1;
}

int main(){
	cin >> n;
	ok=1;
	ktao();
	while(ok){
		if(check1() && check2() && check3()){
			for(int i=1;i<=n;i++){
				cout << a[i];
			}
			cout << endl;
		}
		sinh();
	}
}
