#include<bits/stdc++.h>
using namespace std;
void min(int a, int b){
	string s1="", s2="";
	s1 += to_string(a);
	s2 += to_string(b);
	for(int i=0;i<s1.size();i++){
		if(s1[i]=='6'){
			s1[i]='5';
		}
	}
	for(int i=0;i<s2.size();i++){
		if(s2[i]=='6'){
			s2[i]='5';
		}
	}
	cout << stoi(s1) + stoi(s2) << " ";
}
void max(int a, int b){
	string s1="", s2="";
	s1 += to_string(a);
	s2 += to_string(b);
	for(int i=0;i<s1.size();i++){
		if(s1[i]=='5'){
			s1[i]='6';
		}
	}
	for(int i=0;i<s2.size();i++){
		if(s2[i]=='5'){
			s2[i]='6';
		}
	}
	cout << stoi(s1) + stoi(s2);
}

int main(){
	int a,b;
	cin >> a >> b;
	min(a,b);
	max(a,b);
}
