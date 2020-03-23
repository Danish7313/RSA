/*package com.example.logindemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookingInfoAdapter extends ArrayAdapter<Checkout> {
    private Activity context;
    private List<Checkout> bookingList;

    public BookingInfoAdapter(Activity context,List<Checkout>bookingList){
        super(context, R.layout.list_view, bookingList);
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.list_view,null,true);

        TextView car = (TextView)listView.findViewById(R.id.tvbc);
        TextView time = (TextView)listView.findViewById(R.id.tvbt);
        TextView date = (TextView)listView.findViewById(R.id.tvbd);
        TextView pickup = (TextView)listView.findViewById(R.id.tvbp);

        Checkout booking = bookingList.get(position);
        car.setText(booking.getCarz());
        time.setText(booking.getTimez());
        date.setText(booking.getDatez());
        pickup.setText(booking.getPickupz());

        return  listView;

    }
}
*/