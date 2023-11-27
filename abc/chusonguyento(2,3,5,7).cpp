#include<bits/stdc++.h>
using namespace std;

int n,res,a[15];

int b[5]={0,2,3,5,7};

int check(int i){
	if(i%2==0) return 0;
	int d[10]={};
	while(i>0){
		d[i%10]++;
		i/=10;
	} 
	if(d[2]>0 && d[3]>0 && d[5]>0 && d[7]>0) return 1;
	return 0;
}

void Try(int i){
	if(i<=n){
		for(int j=1;j<=4;j++){
			res=res*10+b[j];
			if(i==n){
				if(check(res)){
					cout << res << endl;
				}
			}
			else Try(i+1);
			res /= 10;
		}
	}
	
}

int main(){
	cin >> n;
	res=0;
	Try(1);
}
