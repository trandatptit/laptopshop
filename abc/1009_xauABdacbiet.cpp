#include<bits/stdc++.h>
using namespace std;
int n,k,ok,a[30];
void ktao(){
	for(int i=1;i<=n;i++){
		a[i]=0;
	}
}
void sinh(){
	int i=n;
	while(i>=1 && a[i]==1){
		a[i]=0;
		i--;
	}
	if(i==0) ok=0;
	else a[i]=1;
}
bool check1(){
	int i=1,t=0,solan=0;
	while(i<=n){
		while(a[i]==1) i++;t=0;
		while(a[i]==0 && i<=n){
			t++;i++;
		}
		if(t==k) solan++;
		
	}	
	if(solan==1) return 1;
	return 0;
}

int main(){
	cin >> n >> k;
	ktao();
	ok=1;
	int dem=0;
	vector<string> v;
	
	while(ok){
		string s="";
		if(check1()){
			dem++;
			for(int i=1;i<=n;i++){
				s+=char(a[i]+65);
			}
			v.push_back(s);
		}
		sinh();
	}
	cout << dem<< endl;
	for(int i=0;i<v.size();i++){
		cout << v[i] << endl;
	}
}
