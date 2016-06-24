package com.aihook.tableview.lib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDataAdapter extends BaseTableAdapter {

	private final Context context;
	private final LayoutInflater inflater;
	private int height;
	private float density;
	private int[] widths;
	private ArrayList<ArrayList<String>> mList;

	public TableDataAdapter(Context context, ArrayList<ArrayList<String>> list) {
		this(context, list, null);
	}

	public TableDataAdapter(Context context, ArrayList<ArrayList<String>> list, int[] widths) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.density = context.getResources().getDisplayMetrics().density;
		this.height = Math.round(density * 40);
		this.mList = list;
		if (widths == null) {
			this.widths = new int[mList.get(0).size()];
			Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mTextPaint.setColor(Color.WHITE);
			String displayText;
			int widthTemp = 0;
			for (int i = 0; i < mList.size(); i++) {
				for (int j = 0; j < mList.get(i).size(); j++) {
					displayText = mList.get(i).get(j);
					widthTemp = Math.round((mTextPaint.measureText(displayText) + (16 * this.density)));
					if (widthTemp > this.widths[j]) {
						this.widths[j] = widthTemp;
					}
				}

			}
		} else {
			this.widths = widths;
		}

	}

	public Context getContext() {
		return context;
	}

	public LayoutInflater getInflater() {
		return inflater;
	}

	@Override
	public int getRowCount() {
		return mList.size() - 1;
	}

	@Override
	public int getColumnCount() {
		return mList.get(0).size() - 1;
	}

	@Override
	public View getView(int row, int column, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(getLayoutResource(row, column), parent, false);
		}
		setText(convertView, mList.get(row + 1).get(column + 1));
		return convertView;
	}

	private void setText(View view, final String text) {
		TextView tv = (TextView) view.findViewById(android.R.id.text1);
		tv.setText(text);
	}

	@Override
	public int getWidth(int column) {
		return Math.round(widths[column + 1] * density);
	}

	@Override
	public int getHeight(int row) {
		return height;
	}

	@Override
	public int getItemViewType(int row, int column) {
		if (row < 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	public int getLayoutResource(int row, int column) {
		final int layoutResource;
		switch (getItemViewType(row, column)) {
		case 0:
			layoutResource = R.layout.table_data_item_header;
			break;
		case 1:
			layoutResource = R.layout.table_data_item;
			break;
		default:
			throw new RuntimeException("wtf?");
		}
		return layoutResource;
	}

}
