#include<bits/stdc++.h>
using namespace std;

int n,ok;
char a[30],k;
void ktao(){
	for(int i=1;i<=n;i++){
		a[i]='O';
	}
}

void sinh(){
	int i=n;
	while(i>=1&&a[i]=='X') {
		a[i]='O';
		i--;
	}
	if(i==0) ok=0;
	else {
		a[i]='X';	
	}
}

bool check(){
	int cnt=0;
	for(int i=1;i<=n;i++){
		if(a[i]=='k') cnt++;
	}
	if(cnt>=5) return 1;
	return 0;
}
int main(){
	cin >>n >>k;
	ok=1;
	ktao();
	while(ok){
		if(check()){
			for(int i=1;i<=n;i++){
			cout << a[i];
			}
			cout << endl;
		}
		sinh();
		cout << endl;
	}
}
