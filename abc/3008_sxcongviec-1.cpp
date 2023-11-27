#include<bits/stdc++.h>
using namespace std;

// code cho bai sx cong viec-1 , luu chon toi uu, truc toa do
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		vector<pair<int ,int >> v(n);
		for(int i=0;i<n;i++){
			int  x, y;
			cin >> x >> y;
			v[i].first=y;
			v[i].second=x;
		}
		
		sort(v.begin(), v.end());
		int dem=1, tmp=v[0].first;
		for(int i=1;i<n;i++){
			if(v[i].second >= tmp){
				dem++;
				tmp=v[i].first;
			}
		}
		cout << dem << endl;
	}
}
