#include<bits/stdc++.h>
using namespace std;
int n,a[50],b[50],xuoi[50],nguoc[50],dem;
void quaylui(int i){
	int j;
	for(j=1;j<=n;j++){
		if(b[j]==0 && xuoi[i-j+n]==0 && nguoc[i+j-1]==0)
		a[i]=j;
		b[j]=1; xuoi[i-j+n]=1; nguoc[i+j-1]=1;
		if(i==n) dem++;
		else quaylui(i+1);
		b[j]=0; xuoi[i-j+n]=0; nguoc[i+j-1]=0;
	}
}

int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		dem=0;
		for(int i=0;i<20;i++){
			b[i]=xuoi[i]=nguoc[i]=0;
		}
		quaylui(1);
		cout << dem<< endl;
	}
}
