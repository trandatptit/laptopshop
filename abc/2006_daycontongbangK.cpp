#include<bits/stdc++.h>
using namespace std;
int n,k,ok,a[30],b[30];
vector<vector<int>> res;
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
bool check(){
	int tmp=0;
	for(int i=1;i<=n;i++){
		if(a[i]==1){
			tmp+=b[i];
		}
	}
	if(tmp==k) return 1;
	return 0;
}
int main(){
	int t;
	cin >> t;
	while(t--){
		cin >>n >>k ;
		for(int i=1;i<=n;i++){
			cin >> b[i];
		}
		sort(b+1,b+n+1);
		ok=1;
		ktao();
		while(ok){
			if(check()){
				vector<int> x;
				for(int i=1;i<=n;i++){
					if(a[i]==1){
						x.push_back(b[i]);
					}
				}
				res.push_back(x);
			}
			sinh();
		}
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
			res.clear();
		}
		
		cout << endl;
	}
}
