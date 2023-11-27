#include<bits/stdc++.h>
using namespace std;

int chuaxet[100];
int n,s=0,a[100][100], x[100];
int MIN=1e9,cmin=1e9;
vector<int> v;
void nhap(){
	cin >> n;
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			cin >> a[i][j];
			if(a[i][j]!=0){
				cmin=min(cmin,a[i][j]);
			}
		}
	}
}

void Try(int i){
	if(s+cmin*(n-i+1) >= MIN) return;
	for(int j=2;j<=n;j++){
		if(chuaxet[j]){
			x[i]=j;
			chuaxet[j]=0;
			s += a[x[i-1]][j];
			if(i==n){
				if(s + a[x[n]][x[1]] < MIN){
					MIN = s + a[x[n]][x[1]];
					v.clear();
					for(int k=1;k<=n;k++){
						v.push_back(x[k]);
					}
					v.push_back(1);
				}
			}
			else Try(i+1);
			s -= a[x[i-1]][j];
			chuaxet[j] = 1;
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
	cout << "(";
	for(int i=0;i<v.size()-1;i++){
		cout << v[i] <<",";
	}
	cout << v[v.size()-1] << ")" ;
	cout << endl;
	cout << MIN;
	return 0;
}
