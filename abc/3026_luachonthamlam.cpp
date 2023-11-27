#include<bits/stdc++.h>
using namespace std;

string MIN(int n, int s){
	if(s>n*9 || n>0 && s==0) return "-1";
	string num(n,'0');
	int tmp = s-9*(n-1);
	if(tmp<0){
		tmp = 1;
	}
	num[0] = tmp+'0' ;
	s -= tmp;
	for(int i=1;i<n;i++){
		tmp = s-9*(n-1-i);
		if(tmp<0){
			tmp = 0;
		}
		num[i] = tmp+'0';
		s -= tmp;
	}
	return num;
}

string MAX(int n, int s){
	if(s>9*n || n>0 && s==0) return "-1";
	string num(n,'0');
	for(int i=0;i<n;i++){
		if(s>9){
			num[i] = 9+'0';
			s -= 9;
		}
		else {
			num[i] = s+'0';
			s=0;
		}
	}
	return num;
}

int main(){
	int n, s;
	cin >> n >> s;
	cout << MIN(n,s) << " " << MAX(n,s) << endl;	
}
