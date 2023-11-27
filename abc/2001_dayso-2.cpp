#include<bits/stdc++.h>
using namespace std;
int n,a[30];
vector<string> v;

void Try(int n,int a[] ){
	if(n>0){
		string s="";
		s=s+"[";
		for(int i=0;i<n-1;i++){
			s=s+to_string(a[i])+ " ";
		}
		s=s+to_string(a[n-1])+"]";
		v.push_back(s);
		for(int i=0;i<n;i++){
			a[i]=a[i]+a[i+1];
		}
		Try(n-1,a);
	}
}
int main(){
	int t;
	cin >> t;
	while(t--){
		cin >> n;
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		Try(n,a);
		for(int i=v.size()-1;i>=0;i--){
			cout << v[i] << " ";
		}
		v.clear();
		cout << endl;
	}
}
