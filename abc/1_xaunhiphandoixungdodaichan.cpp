#include<bits/stdc++.h>
using namespace std;

int ok,a[30];
void ktao(int n){
	for(int i=1;i<=n;i++){
		a[i]=0;
	}
}

void sinh(int n){
	int i=n;
	while(i>=1&&a[i]==1) {
		a[i]=0;
		i--;
	}
	if(i==0) ok=0;
	else {
		a[i]=1;	
	}
}

bool check(int n){
	for(int i=1;i<=n/2;i++){
		if(a[i]!=a[n-i+1]){
			return 0;
		}
	}
	return 1;
}
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >>n;
		for(int tmp=2;tmp<=n;tmp+=2){
			ok=1;
			ktao(tmp);
			while(ok){
				if(check(tmp)){
					for(int i=1;i<=tmp;i++){
						cout << a[i];
					}
					cout <<" ";
				}
				sinh(tmp);
			}
		}
		cout << endl;
	}
	
}
