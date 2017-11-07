package com.kivii.grabdoll.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.MachineGroup;
import com.kivii.grabdoll.core.bean.Toys;
import com.kivii.grabdoll.core.dao.MachineGroupDao;
import com.kivii.grabdoll.core.dao.ToysDao;
import com.kivii.grabdoll.databinding.DialogInputTextBinding;
import com.kivii.grabdoll.ui.adapter.ToysManagerAdapter;
import com.kivii.grabdoll.util.DaoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ToysManagerActivity extends BaseActivity {
    private Context mContext;
    private RecyclerView mRvContent;
    private ToysManagerAdapter mAdapter;
    private List<Toys> mToysList = new ArrayList<>();

    private MachineGroup mGroup;
    private ToysDao toysDao;
    private int moveFromPosition, moveToPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys_manager);
        mContext = this;

        MachineGroupDao groupDao = DaoUtils.daoSession.getMachineGroupDao();
        toysDao = DaoUtils.daoSession.getToysDao();

        long groupId = getIntent().getLongExtra("groupId", 0L);
        mGroup = groupDao.load(groupId);
        if (mGroup == null) {
            finish();
            return;
        }

        setTitleName(mGroup.getName());
        setRightIc(R.string.ic_plus, v -> addToys());

        initView();
        initData();
        initDrag();
    }

    private void initView() {
        mRvContent = findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvContent.setAdapter(mAdapter = new ToysManagerAdapter(mContext, mToysList));
        mAdapter.setOnEditClickListener(this::updateToys);
    }

    private void initData() {
        List<Toys> list = mGroup.getToysList();
        if (list.isEmpty()) {
            addToys();
        } else {
            Collections.sort(list, ((o1, o2) ->
                    Integer.valueOf(o1.getSortNum()).compareTo(o2.getSortNum())));
            mToysList.clear();
            mToysList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initDrag() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int moveFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(moveFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // 拖动过程中的位置变化，
                int fPos = viewHolder.getAdapterPosition();
                int tPos = target.getAdapterPosition();
                moveToPosition = tPos; // 随时记录被拖拽 Item 的终止位置
                // 做出对应的动画效果
                mAdapter.notifyItemMoved(fPos, tPos);
                return true;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                // 当开始拖拽 或结束拖拽时，让手机震动一下
                Vibrator vib = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                if (vib != null) vib.vibrate(50);

                switch (actionState) {
                    case ItemTouchHelper.ACTION_STATE_DRAG: // 拖拽开始，记录被拖拽的 Item 位置
                        moveFromPosition = viewHolder.getAdapterPosition();
                        break;
                    case ItemTouchHelper.ACTION_STATE_IDLE: // 拖拽结束，将数据重新排序
                        if (moveToPosition >= 0 && moveFromPosition != moveToPosition) {
                            mToysList.add(moveToPosition, mToysList.remove(moveFromPosition));
                            reSortList();
                            moveToPosition = -1;
                        }
                        break;
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final Toys toys = mToysList.get(viewHolder.getAdapterPosition());
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage(String.format("是否删除项：%s？", toys.getName()))
                        .setNegativeButton("取消", (dialog, which) -> initData())
                        .setPositiveButton("确定", (dialog, which) -> {
                            toysDao.delete(toys);
                            mGroup.resetToysList();
                        })
                        .show();
            }
        });

        itemTouchHelper.attachToRecyclerView(mRvContent);
    }

    private void reSortList() {
        for (int i = 0, len = mToysList.size(); i < len; i++) {
            Toys toys = mToysList.get(i);
            toys.setSortNum(i);
            toysDao.update(toys);
        }
    }

    private void addToys() {
        final DialogInputTextBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_input_text, null, false);
        new AlertDialog.Builder(mContext)
                .setTitle("添加项")
                .setView(binding.getRoot())
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    String str = binding.etInput.getText().toString();
                    if (TextUtils.isEmpty(str)) {
                        toast("请输入名称");
                    } else {
                        Toys toys = new Toys();
                        toys.setAddTime(new Date());
                        toys.setGroupId(mGroup.getId());
                        toys.setName(str);
                        toys.setSortNum(mToysList.size());
                        toysDao.insert(toys);
                        mGroup.resetToysList();
                        mAdapter.add(toys);
                    }
                })
                .show();
    }

    public void updateToys(Toys toys) {
        final DialogInputTextBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_input_text, null, false);
        binding.setText(toys.getName());
        new AlertDialog.Builder(mContext)
                .setTitle("重命名")
                .setView(binding.getRoot())
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    String str = binding.etInput.getText().toString();
                    if (TextUtils.isEmpty(str)) {
                        toast("请输入名称");
                    } else {
                        toys.setName(str);
                        toysDao.update(toys);
                        mGroup.resetToysList();
                        mAdapter.notifyItemChanged(mToysList.indexOf(toys));
                    }
                })
                .show();
    }
}
