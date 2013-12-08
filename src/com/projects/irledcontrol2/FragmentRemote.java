package com.projects.irledcontrol2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentRemote extends Fragment{

	private int fragmentNR;
	
	public FragmentRemote(int nr) {
		this.fragmentNR = nr;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = new View(getActivity());
		if (fragmentNR == 0)
		v = inflater.inflate(R.layout.fragment_24_key_remote, container, false);
		else if (fragmentNR == 1)
		v = inflater.inflate(R.layout.fragment_44_key_remote, container, false);
				return v;
	}
}
