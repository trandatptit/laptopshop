#include<bits/stdc++.h>
using namespace std;
int n,k,ok,a[30];

void ktao(){
	for(int i=1;i<=k;i++){
		a[i]=i;
	}
}
void sinh(){
	int i=k;
	while(i>=1 && a[i]==n-k+i) i--;
	if(i==0) ok=0; 
	else{
		a[i]++;
		for(int j=i+1;j<=k;j++){
			a[j]=a[j-1]+1;
		}	
	}
}

int main(){
	cin >> n >>k;
	ktao();
	ok=1;
	while(ok){
		for(int i=1;i<=k;i++){
			cout << a[i] <<" ";
		}
		sinh();
		cout << endl;
	}
}
