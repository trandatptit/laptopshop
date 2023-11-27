#include<bits/stdc++.h>
using namespace std;

long long merge(long long a[],int l,int m,int r){
	int n1=m-l+1;
	int n2=r-m;	
	long long L[n1], R[n2];
	for(int i=0;i<n1;i++) L[i] = a[l+i];
	for(int i=0;i<n2;i++) R[i] = a[m+1+i];
	int i=0,j=0,k=l;
	long long kq=0;
	while(i < n1 && j < n2){
		if(L[i] <= R[j]){
			a[k++]=L[i++];
		}
		else{
			a[k]=R[j];
			j++; k++;
			kq += n1-i;
		}
	}
	while(i < n1){
		a[k++]=L[i++];
	}
	while(j < n2){
		a[k++]=R[j++];
	}
	return kq;
}

long long mergesort(long long a[],int l, int r){
	long long kq=0;
	if(l < r){
		int m = (l+r)/2;
		kq += mergesort(a,l,m);
		kq += mergesort(a,m+1,r);
		kq += merge(a,l,m,r);
	}
	return kq;
}

int main(){
	int t;
	cin >> t;
	while(t--){
		int n;
		cin >> n;
		long long a[n];
		for(int i = 0 ; i < n ; i++){
			cin >> a[i] ;
		}
		cout << mergesort(a,0,n-1) << endl;
	}
}
