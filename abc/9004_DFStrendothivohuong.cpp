#include<bits/stdc++.h>
using namespace std;
int n,m,u;
bool used[1001];
vector<int> v[1001];
void nhap(){
	cin >> n >>m >>u;
	
	for(int i=0;i<m;i++){
		int x,y;
		cin >> x >>y;
		v[x].push_back(y);
		v[y].push_back(x);
	}
	memset(used,false,sizeof(used));
}
void dfs(int u){
	cout << u <<" ";
	used[u]=true;
	for(auto x : v[u]){
		if(!used[x]){
			dfs(x);
		}
	}
}
int main(){
	int t;
	cin >> t;
	while(t--){
		nhap();
		dfs(u);
		cout << endl;
		for(int i=0;i<1001;i++){
			v[i].clear();
		}
	}
}
