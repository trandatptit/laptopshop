#include<bits/stdc++.h>
using namespace std;
int chuaxet[100];
int n, s=0, x[100], c[100][100];
int MIN=1e9, cmin=1e9;

void nhap(){
	cin >> n;
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			cin >> c[i][j];
			if(c[i][j]!=0){
				cmin = min(cmin, c[i][j]);
			}
		}
	}
}

void Try(int i){
	if(s + cmin*(n-i+1) >= MIN) return;
	for(int j=2;j<=n;j++){
		if(chuaxet[j]){
			x[i]=j;
			chuaxet[j]=0;
			s += c[x[i-1]][j];
			if(i==n){
				MIN=s;
			}
			else Try(i+1);
			s -= c[x[i-1]][j];
			chuaxet[j]=1;
		}
	}
}

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	memset(chuaxet,1,sizeof(chuaxet));
	chuaxet[1]=0;
	x[1]=1;
	nhap();
	Try(2);
	cout << MIN;
	return 0;
}
