#include<bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int a[n];
		vector<int> res[n];
		int tmp=0;
		for(int i=0; i<n; i++){
			cin >> a[i];
		}
		for(int i=0; i<n-1; i++)
		{
			int check=false;
			for(int j=0; j<n-i-1; j++)
			{
				if(a[j+1]<a[j]){
					check=true;
					swap(a[j],a[j+1]);
				}
			}
			if(!check){
				break;
			}
			for(int j=0; j<n; j++)
			{
				res[i].push_back(a[j]);
			}
			tmp++;
		}
		for(int i=tmp-1;i>=0; i--){
			cout << "Buoc " << i+1 <<": ";
			for(auto x : res[i]){
				cout << x << " ";
			}
			cout << endl;
		}
	}
}
