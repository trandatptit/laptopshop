#include<bits/stdc++.h>
using namespace std;
int n,a[15],b[15];
int u[15]={0};
vector<string> x,v;

void Try1(int i){
	int j;
	for(j=1;j<=n;j++){
		if(!u[j]){
			a[i]=j; u[j]=1;
			if(i==n){
				string s="";
				for(int k=1;k<=n;k++){
					s=s+char(a[k]+64);
				}
				x.push_back(s);
			}
			else Try1(i+1);
			u[j]=0;
		}
	}
}
void Try2(int i){
	int j;
	for(j=1;j<=n;j++){
		b[i]=j;
		if(i==n){
			string s="";
			for(int k=1;k<=n;k++){
				s=s+to_string(b[k]);
			}
			v.push_back(s);
		}
		else Try2(i+1);
	}
}
int main(){
	cin >> n;
	Try1(1);
	Try2(1);
	for(int i=0; i<x.size(); i++){
		for(int j=0;j<v.size();j++){
			cout <<x[i] <<v[j] << endl;
		}
	}
	
}
