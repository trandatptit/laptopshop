#include<bits/stdc++.h>
using namespace std;
int n,m,a[15],b[15]={0};
vector<string> v;
void in(){
	string s='';
	for(int i=1;i<n;i++){
		s=s+to_string(a[i])+" ";
	}
	s=s+to_string(a[n]);
	v.push_back(s);
}

void quaylui(int i){
	int j;
	for(j=1;j<=n;j++){
		if(!b[j]){
			a[i]=j; b[j]=1;
			if(i==n) in();
			else quaylui(i+1);
			b[j]=0;
		}
	}
}

int main(){
	cin >> n >> m;
	quaylui(1);
	for(int i=0;i<v.size();i++){
		if((i+1)%m==0) cout << v[i] << endl;
	}
}
