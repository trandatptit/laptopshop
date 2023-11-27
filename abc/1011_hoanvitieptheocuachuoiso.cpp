#include<bits/stdc++.h>
using namespace std;

int main(){
	int t;
	cin >> t;
	while(t--){
		int test;
		string s;
		cin >>test >> s;
		int n=s.size();
		int i=n-2;
		while( s[i]>=s[i+1]) i--;
		if(i<0) cout<<test <<" " << "BIGGEST"<< endl;
		else{
			int j=n-1;
			while(s[j]<=s[i]) j--;
			swap(s[i],s[j]);
			sort(s.begin()+i+1,s.end());
			cout << test << " "<< s ;
			cout << endl;
		}
	}
}
