package com.kivii.grabdoll.ui;

import android.content.Context;
import android.content.Intent;
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
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.core.dao.MachineGroupDao;
import com.kivii.grabdoll.core.dao.OrganizationDao;
import com.kivii.grabdoll.databinding.DialogInputTextBinding;
import com.kivii.grabdoll.ui.adapter.GroupManagerAdapter;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GroupManagerActivity extends BaseActivity {
    private Context mContext;
    private RecyclerView mRvContent;
    private GroupManagerAdapter mAdapter;
    private List<MachineGroup> mGroupList = new ArrayList<>();

    private Organization org;
    private MachineGroupDao groupDao;
    private int moveFromPosition, moveToPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manager);
        mContext = this;
        OrganizationDao orgDao = DaoUtils.daoSession.getOrganizationDao();
        groupDao = DaoUtils.daoSession.getMachineGroupDao();

        long orgId = SPUtils.getLong(Constant.KEY_STORE_ID);
        org = orgDao.loadDeep(orgId);
        if (org == null) {
            finish();
            return;
        }

        initView();
        initData();
        initDrag();
    }

    private void initView() {
        setTitleName("组管理");
        setRightIc(R.string.ic_plus, v -> addGroup());
        mRvContent = findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvContent.setAdapter(mAdapter = new GroupManagerAdapter(mContext, mGroupList));

        mAdapter.setOnEditClickListener(this::updateGroup);
        mAdapter.setOnItemClickListener(this::onGroupClick);
    }

    private void initData() {
        List<MachineGroup> list = org.getGroupList();
        if (!list.isEmpty()) {
            Collections.sort(list, (o1, o2) ->
                    (Integer.valueOf(o1.getSortNum()).compareTo(o2.getSortNum())));
            mGroupList.clear();
            mGroupList.addAll(list);
            mAdapter.notifyDataSetChanged();
        } else {
            addGroup();
        }
    }

    private void onGroupClick(MachineGroup group) {
        Intent intent = new Intent(this, ToysManagerActivity.class);
        intent.putExtra("groupId", group.getId());
        startActivity(intent);
    }

    private void addGroup() {
        final DialogInputTextBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_input_text, null, false);
        new AlertDialog.Builder(mContext)
                .setTitle("添加组")
                .setView(binding.getRoot())
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    String str = binding.etInput.getText().toString();
                    if (TextUtils.isEmpty(str)) {
                        toast("请输入组名");
                    } else {
                        MachineGroup group = new MachineGroup();
                        group.setAddTime(new Date());
                        group.setName(str);
                        group.setSortNum(mGroupList.size());
                        group.setOrgId(org.getId());
                        groupDao.insert(group);
                        org.resetGroupList();
                        mAdapter.add(group);
                    }
                })
                .show();
    }

    private void updateGroup(MachineGroup group) {
        final DialogInputTextBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_input_text, null, false);
        binding.setText(group.getName());

        new AlertDialog.Builder(mContext)
                .setTitle("重命名")
                .setView(binding.getRoot())
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    String str = binding.etInput.getText().toString();
                    if (TextUtils.isEmpty(str)) {
                        toast("请输入组名");
                    } else {
                        group.setName(str);
                        groupDao.update(group);
                        org.resetGroupList();
                        mAdapter.notifyItemChanged(mGroupList.indexOf(group));
                    }
                })
                .show();
    }

    /**
     * 被始化拖拽
     */
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
                            mGroupList.add(moveToPosition, mGroupList.remove(moveFromPosition));
                            reSortList();
                            moveToPosition = -1;
                        }
                        break;
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final MachineGroup group = mGroupList.get(viewHolder.getAdapterPosition());
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage(String.format("是否删除组：%s，以及组下所有项？", group.getName()))
                        .setNegativeButton("取消", (dialog, which) -> initData())
                        .setPositiveButton("确定", (dialog, which) -> {
                            DaoUtils.daoSession.getToysDao().deleteInTx(group.getToysList());
                            groupDao.delete(group);
                            org.resetGroupList();
                        })
                        .show();
            }
        });

        itemTouchHelper.attachToRecyclerView(mRvContent);
    }

    private void reSortList() {
        for (int i = 0, len = mGroupList.size(); i < len; i++) {
            MachineGroup group = mGroupList.get(i);
            group.setSortNum(i);
            groupDao.update(group);
            org.resetGroupList();
        }
    }
}
