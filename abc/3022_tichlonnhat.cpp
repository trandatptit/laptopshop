#include<bits/stdc++.h>
using namespace std;

int main(){
	int n;
	cin >> n;
	int a[n];
	for(int i=0;i<n;i++){
		cin >> a[i];
	}
	sort(a,a+n);
	vector <int> s;
	s.push_back(a[0]*a[1]);
	s.push_back(a[0]*a[1]*a[2]);
	s.push_back(a[0]*a[1]*a[n-1]);
	s.push_back(a[n-1]*a[n-2]*a[n-3]);
	sort(s.begin(),s.end());
	cout << s[s.size()-1];
}
