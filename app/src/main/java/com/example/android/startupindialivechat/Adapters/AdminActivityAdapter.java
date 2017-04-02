package com.example.android.startupindialivechat.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.startupindialivechat.R;
import com.example.android.startupindialivechat.fragments.ChatFragment;
import com.example.android.startupindialivechat.model.ChatMessage;

import java.util.List;

/**
 * Created by abhishekyadav on 02/04/17.
 */

public class AdminActivityAdapter extends FragmentStatePagerAdapter {
    public AdminActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override

    public Fragment getItem(int position) {
        if (position == 0) {
            return ChatFragment.newInstance();
        } else {
            ChatFragment w = ChatFragment.newInstance();
            return w;
        }

    }

    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Active";
        } else {
            return "Pending";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Created by abhishekyadav on 02/04/17.
     */

    public static class MessageAdapter extends ArrayAdapter<ChatMessage> {


        private Activity activity;
        private List<ChatMessage> messages;

        public MessageAdapter(Activity context, int resource, List<ChatMessage> objects) {
            super(context, resource, objects);
            this.activity = context;
            this.messages = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            int layoutResource = 0; // determined by view type
            ChatMessage chatMessage = getItem(position);
            int viewType = getItemViewType(position);

            if (chatMessage.isMine()) {
                layoutResource = R.layout.item_chat_left;
            } else {
                layoutResource = R.layout.item_chat_right;
            }

            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = inflater.inflate(layoutResource, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            //set message content
            holder.msg.setText(chatMessage.getText());

            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            // return the total number of view types. this value should never change
            // at runtime
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            // return a value between 0 and (getViewTypeCount - 1)
            return position % 2;
        }

        private class ViewHolder {
            private TextView msg;

            public ViewHolder(View v) {
                msg = (TextView) v.findViewById(R.id.txt_msg);
            }
        }
    }
}