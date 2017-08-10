package com.ruaho.studyapp.activity;

/**
 * Created by ruaho on 2017/8/10.
 */

public class RecycleViewActivity {

    /*
        RecylerView的简单使用

        1:导包,引入第三方包进来
            compile 'com.android.support:recyclerview-v7:26.0.0-alpha1'
        2:写布局文件
            <android.support.v7.widget.RecyclerView
                android:id="@+id/recylerview"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />
        3:设置管理器
            >1：LinearLayoutManager线性布局管理器 new LinearLayoutManager(MainActivity.this);
            >2：GridLayoutManager网格布局管理器 new GridLayoutManager(MainActivity.this,3);
            >3：StaggeredGridLayoutManager瀑布流布局管理器 new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        4:管理器设置方向
            setOriention();

        5:设置设配器
            private class MyAdapter extends RecyclerView.Adapter{

                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    //5设置adapter
                    View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_text,parent,false);
                    MyViewHolder holder = new MyViewHolder(view);
                    return holder;
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                    MyViewHolder myHolder = (MyViewHolder) holder;
                    myHolder.tvTest.setText("这是第"+position+"个条目!!!");
                    myHolder.tvTest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this,"点击第"+position+"个条目",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public int getItemCount() {
                    return 60;
                }
            }

            里面需要ViewHolder类
            private class MyViewHolder extends RecyclerView.ViewHolder{

                public TextView tvTest;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    tvTest = (TextView) itemView.findViewById(R.id.tv_test);
                }
            }
        6:注意事项:
            >1View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_text,parent,false);
            加载View的时候必须用这个,因为是和父布局绑定在一起的
            >2onBindViewHolder
            条目的点击事件是在这个方法里面处理的
            >3瀑布流布局需要加上间隔问题
                //先设置布局间隔
                SpacesItemDecoration decoration=new SpacesItemDecoration(16);
                recyclerView.addItemDecoration(decoration);
                //继承一个布局
                public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

                private int space;

                public SpacesItemDecoration(int space) {
                    this.space=space;
                }

                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    outRect.left=space;
                    outRect.right=space;
                    outRect.bottom=space;
                    if(parent.getChildAdapterPosition(view)==0){
                        outRect.top=space;
                    }
                }
            }

    * */

}
