#include<bits/stdc++.h>
using namespace std;
int n,dem,a[100];
vector<string> v;

void quaylui(int n,int i,int s){
	for(int j=n;j>=1;j--){
		a[i]=j;
		if(j==s) {
			string s="(";
			for(int k=1;k<i;k++){
				s=s+to_string(a[k])+" ";
			}
			s=s+to_string(a[i])+")";
			v.push_back(s);
		}
		else if(j<s) quaylui(j,i+1,s-j);
	}
}

int main(){
	int  t;
	cin >> t;
	while(t--){
		cin >> n;
		quaylui(n,1,n);
		cout << v.size()<<endl;
		for(int i=0;i<v.size();i++){
			cout << v[i]<<" ";
		}
		cout << endl;
		v.clear();
	}
}
