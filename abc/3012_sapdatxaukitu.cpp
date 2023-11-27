#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int k;
		cin >> k;
		string s;
		cin >> s;
		map<char,int> mp;
		int dem=0;
		for(int i=0;i<s.size();i++){
			mp[s[i]]++;
			if(mp[s[i]]>dem){
				dem=mp[s[i]];
			}
		}
		if(s.size()+1 >= dem*k) cout << "1";
		else cout << "-1";
		cout << endl;
	}
}
