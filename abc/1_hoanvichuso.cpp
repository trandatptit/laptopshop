#include<bits/stdc++.h>
using namespace std;
string s;
int a[10];

int n,k,ok;
void ktao(){
	for(int i=1;i<=n;i++){
		a[i]=i;
	}
}
void sinh(){
	int i=n-1;
	while(i>=1&&a[i]>a[i+1]) i--;
	if(i==0) ok=0;
	else{
		int j=n;
		while(a[i]>a[j]) j--;
		swap(a[i],a[j]);
		sort(a+i+1,a+n+1);//dao nguoc day
	}
}

int main(){
	int t;
	cin >> t;
	while(t--){
		vector<int> v;
		cin >> s;
		s="0"+s;
		n=s.size()-1;
		ok=1;
		ktao();
		while(ok){
			int tmp=0;
			for(int i=1;i<=n;i++){
				tmp=tmp*10+int(s[a[i]]-48);
			}
			v.push_back(tmp);
			sinh();
		}
		sort(v.begin(), v.end());
		for(int i=0;i<v.size();i++){
			cout << v[i] << endl;
		}
	}
}
