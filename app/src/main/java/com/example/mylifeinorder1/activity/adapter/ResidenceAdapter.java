package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getDate;
import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;
import static com.example.mylifeinorder1.util.ViewUtil.showDateOnSelect;

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
import com.example.mylifeinorder1.util.CustomTextWatcher;
import com.example.mylifeinorder1.util.ViewUtil;

import java.util.List;

public class ResidenceAdapter extends RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder> {

    private List<Residence> residenceItems;

    public static class ResidenceViewHolder extends RecyclerView.ViewHolder {

        /*public Residence residence;

        public EditText countryTextView;
        public EditText streetTextView;*/

        public EditText countryEditText;
        public EditText streetEditText;
        public EditText line2EditText;
        public EditText suburbEditText;
        public EditText stateEditText;
        public EditText postCodeEditText;
        public EditText fromDateEditText;
        public EditText toDateEditText;

        public ResidenceViewHolder(@NonNull View itemView) { //, ResidenceAdapter.OnItemClickListener listener
            super(itemView);
            countryEditText = itemView.findViewById(R.id.country_edit_text);
            line2EditText = itemView.findViewById(R.id.line2_edit_text);
            streetEditText = itemView.findViewById(R.id.street_edit_text);
            stateEditText = itemView.findViewById(R.id.state_edit_text);
            suburbEditText = itemView.findViewById(R.id.suburb_edit_text);
            postCodeEditText = itemView.findViewById(R.id.post_code_edit_text);
            fromDateEditText = itemView.findViewById(R.id.from_date_view);
            toDateEditText = itemView.findViewById(R.id.to_date_view);

            showDateOnSelect(fromDateEditText, itemView.getContext());
            showDateOnSelect(toDateEditText, itemView.getContext());
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

        holder.countryEditText.setText(currentItem.getAddress().getCountry());
        holder.streetEditText.setText(currentItem.getAddress().getStreet());
        holder.line2EditText.setText(currentItem.getAddress().getLine2());
        holder.suburbEditText.setText(currentItem.getAddress().getSuburb());
        holder.stateEditText.setText(currentItem.getAddress().getState());
        holder.postCodeEditText.setText(currentItem.getAddress().getPostCode());

        holder.fromDateEditText.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate().toString());
        holder.toDateEditText.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate().toString());

        holder.countryEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setCountry(holder.countryEditText.getText().toString()));
        holder.streetEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setStreet(holder.streetEditText.getText().toString()));
        holder.line2EditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setLine2(holder.line2EditText.getText().toString()));
        holder.suburbEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setSuburb(holder.suburbEditText.getText().toString()));
        holder.stateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setState(holder.stateEditText.getText().toString()));
        holder.postCodeEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setPostCode(holder.postCodeEditText.getText().toString()));
        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(ViewUtil.getDate(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(ViewUtil.getDate(holder.toDateEditText)));

    }


    @Override
    public int getItemCount() {
        return residenceItems.size();
    }
}
