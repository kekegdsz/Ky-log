package com.ky.android.library.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ky.android.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keke
 * @date 2021/3/26 15:41
 * @describe TIPS:
 * @email keke@ky-tech.com.cn
 **/
public class KyViewPrinter implements KyLogPrinter {
    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private KyViewPrinterProvider viewProvider;

    public KyViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewProvider = new KyViewPrinterProvider(rootView, recyclerView);
    }

    /**
     * 获取ViewProvider，通过ViewProvider可以控制log视图的展示和隐藏
     *
     * @return ViewProvider
     */
    @NonNull
    public KyViewPrinterProvider getViewProvider() {
        return viewProvider;
    }

    @Override
    public void print(@NonNull KyLogConfig config, int level, String tag, @NonNull String printString) {

        // 将log展示添加到recycleView
        adapter.addItem(new KyLogMo(System.currentTimeMillis(), level, tag, printString));

        // 滚动到对应的位置
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

        private LayoutInflater inflater;

        private List<KyLogMo> logs = new ArrayList<>();

        LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(KyLogMo logItem) {
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.ky_log_item, parent, false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(LogViewHolder holder, int position) {
            KyLogMo logItem = logs.get(position);

            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        /**
         * 跟进log级别获取不同的高了颜色
         *
         * @param logLevel log 级别
         * @return 高亮的颜色
         */
        private int getHighlightColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case KyLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case KyLogType.D:
                    highlight = 0xffffffff;
                    break;
                case KyLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case KyLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case KyLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }


    private static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView tagView;
        TextView messageView;

        LogViewHolder(View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
