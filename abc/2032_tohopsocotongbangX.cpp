#include<bits/stdc++.h>
using namespace std;
int n,a[100],c[100],x,s;
vector<string> v;

void quaylui(int i,int s){
	int j;
	for(int j=1;j<=n;j++){
		if(a[j] >= c[i-1] && s+a[j]<=x){
			c[i]=a[j];
			s=s+a[j];
			if(s==x){
				string tmp="{";
				for(int k=1;k<i;k++){
					tmp = tmp + to_string(c[k]) + " ";
				}
				tmp = tmp + to_string(c[i]) + "}";
				v.push_back(tmp);
			}
			else quaylui(i+1,s);
			s=s-a[j];
		}
	}
}
int main(){
	int t;
	cin >> t;
	while(t--){
		cin >>n >>x;
		for(int i=1;i<=n;i++){
			cin >> a[i];
		}
		quaylui(1,0);
		if(v.size()>0){
			cout << v.size() << " ";
			for(int i=0;i<v.size();i++){
				cout << v[i] << " ";
			}
		}
		else cout << -1;
		v.clear();
		cout << endl;
	}
}

