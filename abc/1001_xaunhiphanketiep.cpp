#include<bits/stdc++.h>
using namespace std;

int main(){
	int t;
	cin >> t;
	while(t--){
		string s;
		cin >> s;
		s='0'+s;
		int n=s.size();
		for(int i=n-1;i>=0;i--){
			if(s[i]=='0'){
				s[i]='1';
				for(int j=i+1;j<n;j++){
					s[j]='0';
				}
				break;
			}
		}
		for(int i=1;i<n;i++){
			cout << s[i];
		}
		cout << endl;
	}
}
