package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getDate;
import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.activity.LioAdapter;
import com.example.mylifeinorder1.model.Address;
import com.example.mylifeinorder1.model.LioItem;
import com.example.mylifeinorder1.model.Residence;

import java.util.List;

public class ResidenceAdapter extends RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder> {

    private List<Residence> residenceItems;

    public static class ResidenceViewHolder extends RecyclerView.ViewHolder {

        /*public Residence residence;

        public EditText countryTextView;
        public EditText streetTextView;
        public TextView line2TextView;
        public TextView suburbTextView;
        public TextView stateTextView;
        public TextView postCodeTextView;
        public TextView fromDateTextView;
        public TextView toDateTextView;*/

        public EditText countryTextView;
        public EditText streetTextView;

        public ResidenceViewHolder(@NonNull View itemView) { //, ResidenceAdapter.OnItemClickListener listener
            super(itemView);
            countryTextView = itemView.findViewById(R.id.country_text_view);
            streetTextView = itemView.findViewById(R.id.street_text_view);


            /*countryTextView = itemView.findViewById(R.id.countryTextView);
            streetTextView = itemView.findViewById(R.id.streetTextView);
            line2TextView = itemView.findViewById(R.id.line2TextView);
            suburbTextView = itemView.findViewById(R.id.suburbTextView);
            stateTextView = itemView.findViewById(R.id.stateTextView);
            postCodeTextView = itemView.findViewById(R.id.postCodeTextView);
            fromDateTextView = itemView.findViewById(R.id.fromDateView);
            toDateTextView = itemView.findViewById(R.id.toDateView);

            Address address = new Address();
            address.setCountry(getLayoutEditTextValue(countryTextView));
            address.setStreet(getLayoutEditTextValue(streetTextView));
            address.setState(getLayoutEditTextValue(stateTextView));
            address.setLine2(getLayoutEditTextValue(line2TextView));
            address.setSuburb(getLayoutEditTextValue(suburbTextView));
            address.setPostCode(getLayoutEditTextValue(postCodeTextView));
            residence = new Residence(address);
//            residence.setFromDate(getDate(fromDateTextView));
//            residence.setToDate(getDate(toDateTextView));*/
        }
    }

    public ResidenceAdapter(List<Residence> residenceItems) {
        this.residenceItems = residenceItems;
    }

    @NonNull
    @Override
    public ResidenceAdapter.ResidenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.residence_item, parent, false);
        ResidenceViewHolder rvh = new ResidenceViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResidenceAdapter.ResidenceViewHolder holder, int position) {
        Residence currentItem = residenceItems.get(position);
        /*holder.countryTextView.setText(currentItem.getCountry());
        holder.streetTextView.setText(currentItem.getStreet());
        holder.line2TextView.setText(currentItem.getAddress().getLine2());
        holder.suburbTextView.setText(currentItem.getAddress().getSuburb());
        holder.stateTextView.setText(currentItem.getAddress().getState());
        holder.postCodeTextView.setText(currentItem.getAddress().getPostCode());

//        holder.fromDateTextView.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate().toString());
//        holder.toDateTextView.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate().toString());

        holder.residence.setAddress(currentItem.getAddress());*/

        holder.countryTextView.setText(currentItem.getAddress().getCountry());
        holder.streetTextView.setText(currentItem.getAddress().getStreet());


        holder.countryTextView.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setCountry(holder.countryTextView.getText().toString()));
        holder.streetTextView.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setStreet(holder.streetTextView.getText().toString()));
    }

    private interface CustomTextWatcher extends TextWatcher {
        public default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public abstract void onTextChanged(CharSequence charSequence, int i, int i1, int i2);
        @Override
        public default void afterTextChanged(Editable editable) {

        }
    }

    @Override
    public int getItemCount() {
        return residenceItems.size();
    }
}
