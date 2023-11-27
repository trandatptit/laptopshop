#include<bits/stdc++.h>
using namespace std;

int n,ok,a[15],b[15];

void ktao(){
	for(int i=1;i<=n;i++){
		a[i]=i;
	}
}
void sinh(){
	int i=n-1;
	while(i>=1 && a[i]>a[i+1]) i--;
	if(i==0) ok=0;
	else {
		int j=n;
		while(a[j]<a[i]) j--;
		swap(a[i],a[j]);
		sort(a+i+1,a+n+1);
	}
}
bool check(){
	for(int i=1;i<=n;i++){
		if(a[i]!=b[i]) return 1;
	}
	return 0;
}
int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		for(int i=1;i<=n;i++){
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
