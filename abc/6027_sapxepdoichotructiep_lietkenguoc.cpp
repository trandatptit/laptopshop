#include<bits/stdc++.h>
using namespace std;

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		for(int i=0;i<n;i++){
			cin >> a[i];
		}
		vector<string> v;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				if(a[i]>a[j]){
					swap(a[j],a[i]);
				}
			}
			
			string tmp="";
			tmp=tmp+"Buoc " + to_string(i+1) + ": ";
			for(int j=0;j<n;j++){
				tmp=tmp+to_string(a[j])+" ";
			}
			v.push_back(tmp);
		}
		for(int i=v.size()-1;i>=0;i--){
			cout << v[i] << endl;
		}
	}
}
