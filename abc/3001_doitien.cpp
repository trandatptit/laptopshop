#include<bits/stdc++.h>
using namespace std;
int a[10] = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000};
int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		int dem=0;
		int i=9;
		while( n>0){
			if(n>=a[i]){
				dem++;
				n-=a[i];
			}
			else i--;
		}
		cout << dem << endl;
	}
}
