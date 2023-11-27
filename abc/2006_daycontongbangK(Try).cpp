#include<bits/stdc++.h>
using namespace std;
int n,k,a[30],v[30];
vector<vector<int>> res;
void ktao(){
	res.clear();
	cin >> n>>k;
	for(int i=1;i<=n;i++){
		cin >> v[i];
	}
	sort(v+1,v+n+1);
}
void Try(int i){
	for(int j=0;j<=1;j++){
		a[i]=j;
		if(i==n){
			int s=0;
			for(int l=1;l<=n;l++){
				if(a[l]==1) s=s+v[l]; 
			}
			if(s==k){
				vector<int> x;
				for(int l=1;l<=n;l++){
					if(a[l]==1) x.push_back(v[l]);
				}
				res.push_back(x);
			}
		}
		else Try(i+1);
	}
}
int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin >> t;
	while(t--){
		ktao();
		Try(1);
		if(res.size()==0) cout << "-1";
		else{
			sort(res.begin(),res.end());
			
			for(int i=0;i<res.size();i++){
				cout << "[";
				for(int j=0;j<res[i].size()-1;j++){
					cout << res[i][j] <<" ";
				}
				cout << res[i][res[i].size()-1] <<"] ";
			}
		}
		cout << endl;
	}
}
