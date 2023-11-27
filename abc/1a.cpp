/*
e=max(a[i],e+a[i])
s=max(s[i-1],e[i])
*/
#include<bits/stdc++.h>
using namespace std;

int qhd(string s1, string s2,string s3){
	int c[s1.size()+1][s2.size()+1][s3.size()+1];
	memset(c,0,sizeof(c));
	int i,j,k;
	for( i=1;i<=s1.size();i++){
		for( j=1;j<=s2.size();j++){
			for( k=1;k<=s3.size();k++){
				if(s1[i-1]==s2[j-1] && s2[j-1]==s3[k-1]) c[i][j][k]=c[i-1][j-1][k-1]+1;
				else {
					c[i][j][k]=max(c[i-1][j][k], max(c[i][j][k-1],c[i][j-1][k]));
				} 
			}
		}
	}
	return c[s1.size()][s2.size()][s3.size()];
}
int main(){
	int t;
	cin >> t;
	cin.ignore();
	while(t--){
		int n,m,y;
		string a,b,c;
		cin >> n >>m >>y >> a >> b >> c;
		cout << qhd(a,b,c) << endl;
	}
	
}
