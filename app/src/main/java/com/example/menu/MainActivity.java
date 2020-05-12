package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int [] Options={1,2,3,4,5};
    String [] Means={"保存","另存为","分享","删除","退出"};
//    1 保存
//    2 另存为
//    3 分享
//    4 删除
//    5 退出
    TextView Context_text;
    EditText editText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context_text=findViewById(R.id.id_Context_menu);
        registerForContextMenu(Context_text);

        editText=findViewById(R.id.id_edit_view);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,editText);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.first:
                                diy_toast("你点击了信阳师范学院",Toast.LENGTH_SHORT);
                                editText.setText("信阳师范学院");
                                break;
                            case R.id.second:
                                diy_toast("你点击了上海交通大学",Toast.LENGTH_SHORT);
                                editText.setText("上海交通大学");

                                break;
                            case R.id.third:
                                diy_toast("你点击了中山大学",Toast.LENGTH_SHORT);
                                editText.setText("中山大学");
                                break;
                            case R.id.forth:
                                diy_toast("你点击了浙江大学",Toast.LENGTH_SHORT);
                                editText.setText("浙江大学");
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }


//    选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        for (int i = 0; i < Options.length; i++) {
            menu.add(1,Options[i],i,Means[i]);
        }
        SubMenu subMenu=menu.addSubMenu(1,6,Options.length,"more");
        subMenu.add(1,-1,1,"test1");
        subMenu.add(1,-1,2,"test2");
        subMenu.add(1,-1,3,"test3");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case 1:
                diy_toast(Means[0],Toast.LENGTH_SHORT);
                break;
            case 2:
                diy_toast(Means[1],Toast.LENGTH_SHORT);
                break;
            case 3:
                diy_toast(Means[2],Toast.LENGTH_SHORT);
                break;
            case 4:
                diy_toast(Means[3],Toast.LENGTH_SHORT);
                break;
            case 5:
                diy_toast(Means[4],Toast.LENGTH_SHORT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                diy_toast("已保存",Toast.LENGTH_SHORT);
                break;
            case R.id.save_as:
                diy_toast("另存为已完成！",Toast.LENGTH_SHORT);
                break;
            case R.id.edit:
                diy_toast("请编辑",Toast.LENGTH_SHORT);
                break;
        }
        return super.onContextItemSelected(item);
    }

    //    自定义Toast
    public void diy_toast(String str,int show_time){
        View view=getLayoutInflater().inflate(R.layout.my_diy_toast,(ViewGroup)findViewById(R.id.id_diy_toast));
        TextView textView=view.findViewById(R.id.textView);
        textView.setText(str);
        Toast toast=new Toast(MainActivity.this);
        toast.setDuration(show_time);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setView(view);
        toast.show();
    }
}
