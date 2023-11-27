#include<bits/stdc++.h>
using namespace std;
int n,ok,a[30];
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

int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		ok=1;
		vector<string> v;
		ktao();
		while(ok){
			string s="";
			for(int i=1;i<=n;i++){
				s+=to_string(a[i]);
			}
			v.push_back(s);
			sinh();
		}
		for(int i=v.size()-1;i>=0;i--){
			cout << v[i] <<" ";
		}
		cout << endl;
	}
}
