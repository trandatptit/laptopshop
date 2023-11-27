#include<bits/stdc++.h>
using namespace std;
bool check[10];

void ktao(){
	for(int i=0;i<10;i++){
		check[i]=true;
	}
}
bool check1(string s){
	for(int i=0;i<s.size();i++){
		if(s[s.size()-1]=='2') return false;
	}
	return true;
}
bool check2(string s){
	for(int i=0;i<s.size();i++){
		if(s[i]=='2') check[2]=false;
		else if(s[i]=='3') check[3]=false;
		else if(s[i]=='5') check[5]=false;
		else if(s[i]=='7') check[7]=false;
	}
	if(check[2]==true) return false;
	else if(check[3]==true) return false;
	else if(check[5]==true) return false;
	else if(check[7]==true) return false;
	return true;
}
void nt(int i){
	queue<string> q;
	q.push("2");
	q.push("3");
	q.push("5");
	q.push("7");
	while(!q.empty()){
		string s=q.front(); q.pop();
		ktao();
		if(s.size()==i && check1(s) && check2(s)){
			cout << s << endl;
		} 
		else if(s.size()>i) break;
		q.push(s+"2");
		q.push(s+"3");
		q.push(s+"5");
		q.push(s+"7");
	}
}

int main(){
	int n;
	cin >> n;
	for(int i=4;i<=n;i++){
		nt(i);
	}
}
