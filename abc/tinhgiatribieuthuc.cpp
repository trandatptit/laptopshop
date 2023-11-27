#include <bits/stdc++.h>
using namespace std;

void hauto(vector<string> s){
	stack<long long> stk;
	int len=s.size();
	for(int i=0;i<len;i++){
		if(s[i]=="+" || s[i]=="-" || s[i]=="/" || s[i]=="*" || s[i]=="%"|| s[i]=="^"){
			long long a=stk.top();stk.pop();
			long long b=stk.top();stk.pop();
			long long tmp;
			if(s[i]=="+") tmp=b+a;
			else if(s[i]=="-") tmp=b-a;
			else if(s[i]=="*") tmp=b*a;
			else if(s[i]=="/") tmp=b/a;
			else if(s[i]=="%") tmp=b%a;
			else if(s[i]=="^") {
				tmp=b;
				for(int i=0;i<a-1;i++){
					tmp*=b;
				}	
			}
			stk.push(tmp);
		}
		else {
			string tmp=s[i];
			long long x = stoll(tmp);
			stk.push(x);
		}	
	}
	cout << stk.top() << endl;
}

void tiento(vector<string> s){
	stack<long long> stk;
	int len=s.size();
	for(int i=len-1;i>=0;i--){
		if(s[i]=="+" || s[i]=="-" || s[i]=="/" || s[i]=="*" || s[i]=="%"|| s[i]=="^"){
			long long a=stk.top();stk.pop();
			long long b=stk.top();stk.pop();
			long long tmp;
			if(s[i]=="+") tmp=a+b;
			else if(s[i]=="-") tmp=a-b;
			else if(s[i]=="*") tmp=a*b;
			else if(s[i]=="/") tmp=a/b;
			else if(s[i]=="%") tmp=a%b;
			else if(s[i]=="^") {
				tmp=a;
				for(int i=0;i<b-1;i++){
					tmp*=a;
				}	
			}
			stk.push(tmp);
		}
		else {
			string tmp=s[i];
			long long x=stoll(tmp);
			stk.push(x);
		}
	}
	cout << stk.top() << endl;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		vector<string> s;
		for(int i=0;i<n;i++){
			string tmp;
			cin >> tmp;
			s.push_back(tmp);
		}
		if(s[0]=="+" || s[0]=="-" || s[0]=="/" || s[0]=="*" || s[0]=="%"|| s[0]=="^"){
			tiento(s);
		}
		else {
			hauto(s);
		}
	}
}

