package weizexu.baway.com.weizexu20170825.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weizexu.baway.com.weizexu20170825.R;

/**
 * Created by asus on 2017/8/25.
 */

public class Fragment03 extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.f03,container,false);
        return view;
    }
}
