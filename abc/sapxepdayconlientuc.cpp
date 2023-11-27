#include<bits/stdc++.h>
using namespace std;


int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n],b[n];
		vector<int> v;
		for(int i=0;i<n;i++){
			int x;
			cin >> x;
			a[i]=x;
			b[i]=x;
		}
		sort(b,b+n);
		for(int i=0;i<n;i++){
			if(a[i]!=b[i]){
				v.push_back(i);
			}
		}
		cout << v.front()+1 << " " << v.back()+1;
		cout << endl;
	}
}
