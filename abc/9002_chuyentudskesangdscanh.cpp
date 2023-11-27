#include<bits/stdc++.h>
using namespace std;
vector<pair<int,int>> v;
int main(){
	int n;
	cin >> n;
	cin.ignore();
	for(int i=1;i<=n;i++){
		string s,num;
		getline(cin,s);
		stringstream ss(s);
		while(ss>>num){
			if(stoi(num)>i){
				v.push_back({i,stoi(num)});
			}
		}
	}
	for(auto x : v){
		cout << x.first <<" " << x.second << endl;
	}
}
