package weizexu.baway.com.weizexu20170825;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import weizexu.baway.com.weizexu20170825.fragment.Fragment01;
import weizexu.baway.com.weizexu20170825.fragment.Fragment02;
import weizexu.baway.com.weizexu20170825.fragment.Fragment03;

public class MainActivity extends AppCompatActivity {
    private RadioButton but1,but2,but3;
    private TextView tv1,tv2,tv3;
    private ViewPager pager;
    private List<Fragment> list = new ArrayList<>();
    private String[] sz={"圈子","朋友","我的"};
    private TabLayout mytab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       pager = (ViewPager) findViewById(R.id.viewpager);
        mytab = (TabLayout) findViewById(R.id.mytab);
        mytab.setupWithViewPager(pager);
        list.add(new Fragment01());
        list.add(new Fragment02());
        list.add(new Fragment03());
        mytab.addTab(mytab.newTab().setIcon(R.drawable.ic_launcher));
        mytab.addTab(mytab.newTab().setIcon(R.drawable.ic_launcher));
        mytab.addTab(mytab.newTab().setIcon(R.drawable.ic_launcher));
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return sz[position];
            }
        });
        mytab.getTabAt(0).setIcon(R.drawable.ic_launcher);
        mytab.getTabAt(1).setIcon(R.drawable.ic_launcher);
        mytab.getTabAt(2).setIcon(R.drawable.ic_launcher);
        boolean b = checkNet();
        if (!b) {
//            使用对话框判断是否可以操作
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("是否去设置网络");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    使用隐士跳转自动跳转到设置网络界面
                    Toast.makeText(MainActivity.this, "设置网络！", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                    startActivity(intent);
                    return;
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "查看本地", Toast.LENGTH_LONG).show();
                }
            });
            builder.create();
            builder.show();
        }else {
            Toast.makeText(this, "网络正处于连接中", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkNet() {
        ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            return true;
        }
        return false;
    }

}
