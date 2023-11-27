#include<bits/stdc++.h>
using namespace std;
int n,a[100], cot[100], xuoi[100], nguoc[100];
int dem=0;

void Try(int i){
	for(int j=1;j<=n;j++){
		if(cot[j]==1 && xuoi[i-j+n]==1 && nguoc[i+j-1]==1){
			a[i]=j;
			cot[j]=xuoi[i-j+n]= nguoc[i+j-1]=0;
			if(i==n)  dem++;
			else Try(i+1);
			cot[j]=xuoi[i-j+n]=nguoc[i+j-1]=1;
		}
	}
}


int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		dem=0;
		for(int i=0;i<=99;i++){
			cot[i]=xuoi[i]=nguoc[i]=1;
		}
		Try(1);
		cout << dem << endl;
	}
}
