#include<bits/stdc++.h>
using namespace std;
int ok,a[15];
void ktao(int n){
	for(int i=1;i<=n;i++){
		a[i]=0;
	}
}
void sinh(int n){
	int i=n;
	while(i>0 && a[i]==1){
		a[i]=0;
		i--;
	}
	if(i==0) ok=0;
	else a[i]=1;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		for(int i=1;i<=n;i++){
			if(i%2==0){
				int tmp=i/2;
				ok=1;
				ktao(tmp);
				while(ok){
					string s="";
					for(int i=1; i<=tmp;i++){
						s=s+to_string(a[i]);
					}
					string m=s;
					reverse(m.begin(),m.end());
					s=s+m;
					cout << s << " ";
					sinh(tmp);
				}
			}
		}
		cout << endl;
	}	
}
