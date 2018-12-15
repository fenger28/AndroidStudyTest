package cn.fenger7.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.greendao.AbstractDaoMaster;

import java.util.List;

public class GreenDaoTestActivity extends AppCompatActivity {

    private Button insert;
    private Button delete;
    private Button update;
    private Button search;
    private UserDao dao;
    private User user;
    private User user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    /**
     * 初始化jia监听
     */
    private void initListener() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.insert(user);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.delete(user);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.update(user1);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = dao.queryBuilder().build().list();
                System.out.println("name:"+users.get(0).getName());
                Log.d("name",users.get(0).getName());
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,
                "text.db", null);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();

        dao = daoSession.getUserDao();

        user = new User();
        user.setId(1L);
        user.setAge(15);
        user.setName("fei");
        user1 = new User();
        user1.setId(1L);
        user1.setAge(15);
        user1.setName("xiao");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        insert = findViewById(R.id.insert);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        search = findViewById(R.id.search);
    }
}
