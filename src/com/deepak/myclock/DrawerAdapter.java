package com.deepak.myclock;

import java.util.ArrayList;

import com.deepak.myclock.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

	private static final int TYPE_HEADER = 0;

	private static final int TYPE_ITEM = 1;
	private Context context;
	private ArrayList<Items> navDrawerItems;

	private String name;
	private String email;
	private OnItemClickListener mListener;

	public interface OnItemClickListener {
		public void onClick(View view, int position);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		int Holderid;

		TextView textView;
		ImageView imageView;
		ImageView profile;
		TextView Name;
		TextView email;
		RelativeLayout drawerItem;

		public ViewHolder(View itemView, int ViewType) {
			super(itemView);

			if (ViewType == TYPE_ITEM) {
				
				drawerItem = (RelativeLayout)itemView.findViewById(R.id.drawer_item );
				imageView = (ImageView) itemView.findViewById(R.id.item_icon);
				textView = (TextView) itemView.findViewById(R.id.item_text);
				Holderid = 1;
			} else {

				Name = (TextView) itemView.findViewById(R.id.UserNameText);
				Holderid = 0;
			}
		}

	}

	public DrawerAdapter(Context context, ArrayList<Items> navDrawerItems ,OnItemClickListener listener) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
		mListener = listener;
	}

	@Override
	public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {

		if (viewType == TYPE_ITEM) {
			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.drawer_list_item, parent, false);

			ViewHolder vhItem = new ViewHolder(v, viewType);

			return vhItem; // Returning the created object

		} else if (viewType == TYPE_HEADER) {

			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.header, parent, false);

			ViewHolder vhHeader = new ViewHolder(v, viewType);

			return vhHeader;

		}
		return null;

	}

	@Override
	public void onBindViewHolder(DrawerAdapter.ViewHolder holder, final int position) {
		if (holder.Holderid == 1) {
			holder.textView.setText(navDrawerItems.get(position).getTitle());
			holder.imageView.setImageResource(navDrawerItems.get(position)
					.getIcon());
			
			holder.drawerItem.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                mListener.onClick(view, position);
	            }
	        });
		} else {
			 holder.Name.setText(navDrawerItems.get(position).getTitle());
		}
	}

	@Override
	public int getItemCount() {
		return navDrawerItems.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		if (isPositionHeader(position))
			return TYPE_HEADER;

		return TYPE_ITEM;
	}

	private boolean isPositionHeader(int position) {
		return position == 0;
	}
}
