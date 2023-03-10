package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;
import static com.example.mylifeinorder1.util.ViewUtil.showDateOnSelect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.model.Employment;
import com.example.mylifeinorder1.model.Residence;
import com.example.mylifeinorder1.util.CustomTextWatcher;
import com.example.mylifeinorder1.util.ViewUtil;

import java.util.List;

public class EmploymentAdapter extends RecyclerView.Adapter<EmploymentAdapter.EmploymentViewHolder> {

    private List<Employment> employmentItems;

    public static class EmploymentViewHolder extends RecyclerView.ViewHolder {

        public EditText countryEditText;
        public EditText streetEditText;
        public EditText line2EditText;
        public EditText suburbEditText;
        public EditText stateEditText;
        public EditText postCodeEditText;
        public EditText fromDateEditText;
        public EditText toDateEditText;

        public EditText nameEditText;

        public EditText roleEditText;

        public EditText phoneEditText;

        public EditText emailEditText;

        public EmploymentViewHolder(@NonNull View itemView) {
            super(itemView);
            countryEditText = itemView.findViewById(R.id.country_edit_text);
            line2EditText = itemView.findViewById(R.id.line2_edit_text);
            streetEditText = itemView.findViewById(R.id.street_edit_text);
            stateEditText = itemView.findViewById(R.id.state_edit_text);
            suburbEditText = itemView.findViewById(R.id.suburb_edit_text);
            postCodeEditText = itemView.findViewById(R.id.post_code_edit_text);
            fromDateEditText = itemView.findViewById(R.id.from_date_view);
            toDateEditText = itemView.findViewById(R.id.to_date_view);
            nameEditText = itemView.findViewById(R.id.name_edit_text);
            roleEditText = itemView.findViewById(R.id.role_edit_text);
            phoneEditText = itemView.findViewById(R.id.phone_edit_text);
            emailEditText = itemView.findViewById(R.id.email_edit_text);

            showDateOnSelect(fromDateEditText, itemView.getContext());
            showDateOnSelect(toDateEditText, itemView.getContext());
        }
    }

    public EmploymentAdapter(List<Employment> employmentItems) {
        this.employmentItems = employmentItems;
    }

    @NonNull
    @Override
    public EmploymentAdapter.EmploymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employment_item, parent, false);
        EmploymentAdapter.EmploymentViewHolder rvh = new EmploymentAdapter.EmploymentViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull EmploymentAdapter.EmploymentViewHolder holder, int position) {
        Employment currentItem = employmentItems.get(position);

        holder.countryEditText.setText(currentItem.getAddress().getCountry());
        holder.streetEditText.setText(currentItem.getAddress().getStreet());
        holder.line2EditText.setText(currentItem.getAddress().getLine2());
        holder.suburbEditText.setText(currentItem.getAddress().getSuburb());
        holder.stateEditText.setText(currentItem.getAddress().getState());
        holder.postCodeEditText.setText(currentItem.getAddress().getPostCode());

        holder.nameEditText.setText(currentItem.getName());
        holder.roleEditText.setText(currentItem.getRole());
        holder.phoneEditText.setText(currentItem.getNumber());
        holder.emailEditText.setText(currentItem.getEmailAddress());

        holder.fromDateEditText.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate().toString());
        holder.toDateEditText.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate().toString());

        holder.countryEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setCountry(getLayoutEditTextValue(holder.countryEditText)));
        holder.streetEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setStreet(getLayoutEditTextValue(holder.streetEditText)));
        holder.line2EditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setLine2(getLayoutEditTextValue(holder.line2EditText)));
        holder.suburbEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setSuburb(getLayoutEditTextValue(holder.suburbEditText)));
        holder.stateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setState(getLayoutEditTextValue(holder.stateEditText)));
        holder.postCodeEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setPostCode(getLayoutEditTextValue(holder.postCodeEditText)));
        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(ViewUtil.getDate(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(ViewUtil.getDate(holder.toDateEditText)));

        holder.nameEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setName(getLayoutEditTextValue(holder.nameEditText)));
        holder.roleEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setRole(getLayoutEditTextValue(holder.roleEditText)));
        holder.phoneEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setNumber(getLayoutEditTextValue(holder.phoneEditText)));
        holder.emailEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setEmailAddress(getLayoutEditTextValue(holder.emailEditText)));

    }

    @Override
    public int getItemCount() {
        return employmentItems.size();
    }
}
