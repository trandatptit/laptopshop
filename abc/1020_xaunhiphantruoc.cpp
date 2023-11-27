
#include<bits/stdc++.h>
using namespace std;

int main(){
	int t;
	cin >> t;
	while(t--){
		string s;
		cin >> s;
		s='1'+s;
		int n=s.size();
		for(int i=n-1;i>=0;i--){
			if(s[i]=='1'){
				s[i]='0';
				for(int j=i+1;j<=n;j++){
					s[j]='1';
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
