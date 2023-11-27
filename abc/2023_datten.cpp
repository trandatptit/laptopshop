#include<bits/stdc++.h>
using namespace std;
int m,k,n,a[30];
vector <string> v;
set <string> s;
void nhap(){
	for(int i=0;i<m;i++){
		string x; cin >> x;
		if(s.find(x)==s.end()){
			s.insert(x);
		}
	}
	for(auto x:s){
		v.push_back(x);
	}
	sort(v.begin(),v.end());
}

void in(){
	for(int i=1;i<=k;i++){
		cout << v[a[i]-1] << " ";
	}
	cout << endl;
}
void Try(int i){
	int j;
	for(j=a[i-1]+1;j<=n-k+i;j++){
		a[i]=j;
		if(i==k) in();
		else Try(i+1);
	}
}
int main(){
	cin >> m >> k;
	nhap();
	n=v.size();
	Try(1);
}
