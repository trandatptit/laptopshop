#include<bits/stdc++.h>
using namespace std;

int n,ok,a[30];
void ktao(){
	for(int i=1;i<=n;i++){
		a[i]=0;
	}
}

void sinh(){
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

int main(){
	int t;
	cin >> t;
	while(t--){
		
		cin >>n;
		ok=1;
		ktao();
		vector<string> v;
		
		while(ok){
			string s="";
			for(int i=1;i<=n;i++){
				s+=char(a[i]+65);
			}
			v.push_back(s);
			sinh();
		}
		for(int i=0;i<v.size();i++){
			cout << v[i];
			if(i<v.size()-1) {
				cout << ",";
			}
		}
		cout << endl;
	}
	
}
