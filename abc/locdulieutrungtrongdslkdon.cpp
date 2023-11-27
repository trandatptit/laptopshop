#include<bits/stdc++.h>
using namespace std;
int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int n,x;
	cin >> n;
	map<int,int> mp;
	int a[n];
	for(int i=0;i<n;i++){
		cin >> x;
        mp[x]++;
        if (mp[x] == 1)
            cout << x << " ";
	}
}
