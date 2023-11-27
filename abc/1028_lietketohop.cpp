#include<bits/stdc++.h>
using namespace std;

int main(){
	int n,k;
	cin >> n>> k;
	int x;
	vector<int> v;
	map<int,int> mp;
	for(int i=0;i<n;i++){
		cin >> x;
		mp[x]++;
	}
	for(auto i:mp){
		v.push_back(i.first);
	}
	int a[k+1];
	int ok=1;
	for(int i=1;i<=k;i++){
		a[i]=i;
	}
	int m=v.size();
	while(ok){
		for(int i=1;i<=k;i++){
			cout << v[a[i]-1] <<" ";
		}
		cout << endl;
		int i=k;
		while(i>=1 && a[i]==m-k+i) i--;
		if(i==0) ok=0; 
		else{
			a[i]++;
			for(int j=i+1;j<=k;j++){
				a[j]=a[j-1]+1;
			}	
		}
	}
}
