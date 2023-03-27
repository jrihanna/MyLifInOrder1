package com.mlio.mylifeinorder.mylifeinorder1.activity.adapter;

import static com.mlio.mylifeinorder.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mlio.mylifeinorder.mylifeinorder1.R;
import com.mlio.mylifeinorder.mylifeinorder1.model.Employment;
import com.mlio.mylifeinorder.mylifeinorder1.util.CustomTextWatcher;
import com.mlio.mylifeinorder.mylifeinorder1.util.ViewUtil;

import java.util.List;

public class EmploymentAdapter extends RecyclerView.Adapter<EmploymentAdapter.EmploymentViewHolder> {

    private List<Employment> employmentItems;

    private EmploymentAdapter.OnDeleteItemClickListener mDeleteListener;

    public interface OnDeleteItemClickListener {
        void onItemClick(int position);
    }
    public void setOnDeleteItemClickListener(EmploymentAdapter.OnDeleteItemClickListener listener) {
        mDeleteListener = listener;
    }


    public static class EmploymentViewHolder extends RecyclerView.ViewHolder {

        public Spinner countrySpinner;
        public EditText streetEditText;
        public EditText line2EditText;
        public EditText suburbEditText;
        public EditText stateEditText;
        public EditText postCodeEditText;
        public EditText fromDateEditText;
        public EditText toDateEditText;

        public ImageView clearToDate;

        public EditText nameEditText;

        public EditText roleEditText;

        public EditText phoneEditText;

        public EditText emailEditText;

        public RelativeLayout contentSection;
        public RelativeLayout headerSection;

        public TextView cardTitle;

        public ImageButton deleteButton;

        public EmploymentViewHolder(@NonNull View itemView, EmploymentAdapter.OnDeleteItemClickListener listener) {
            super(itemView);
            countrySpinner = itemView.findViewById(R.id.country_edit_text);
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

            contentSection = itemView.findViewById(R.id.content_section);
            headerSection = itemView.findViewById(R.id.header_section);
            cardTitle = itemView.findViewById(R.id.card_title);
            clearToDate = itemView.findViewById(R.id.clear_to_date);
            deleteButton = itemView.findViewById(R.id.delete_button);

            ViewUtil.showDateOnSelect(fromDateEditText, itemView.getContext());
            ViewUtil.showDateOnSelect(toDateEditText, itemView.getContext());

            clearToDate.setOnClickListener(v -> {
                toDateEditText.setText("");
            });

            deleteButton.setOnClickListener(view -> {
                if(listener != null) {
                    int position = getAbsoluteAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public EmploymentAdapter(List<Employment> employmentItems) {
        this.employmentItems = employmentItems;
    }

    @NonNull
    @Override
    public EmploymentAdapter.EmploymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employment_item, parent, false);
        EmploymentAdapter.EmploymentViewHolder rvh = new EmploymentAdapter.EmploymentViewHolder(v, mDeleteListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull EmploymentAdapter.EmploymentViewHolder holder, int position) {
        Employment currentItem = employmentItems.get(position);

        holder.countrySpinner.setSelection(currentItem.getAddress().getCountry() == null ? 0 : Integer.valueOf(currentItem.getAddress().getCountry()));
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

        holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());

        holder.streetEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setStreet(ViewUtil.getLayoutEditTextValue(holder.streetEditText)));
        holder.line2EditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setLine2(ViewUtil.getLayoutEditTextValue(holder.line2EditText)));
        holder.suburbEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setSuburb(ViewUtil.getLayoutEditTextValue(holder.suburbEditText)));
        holder.stateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setState(ViewUtil.getLayoutEditTextValue(holder.stateEditText)));
        holder.postCodeEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setPostCode(ViewUtil.getLayoutEditTextValue(holder.postCodeEditText)));
        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(ViewUtil.getLayoutEditTextValue(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(ViewUtil.getLayoutEditTextValue(holder.toDateEditText)));

        holder.nameEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setName(ViewUtil.getLayoutEditTextValue(holder.nameEditText)));
        holder.roleEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setRole(ViewUtil.getLayoutEditTextValue(holder.roleEditText)));
        holder.phoneEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setNumber(ViewUtil.getLayoutEditTextValue(holder.phoneEditText)));
        holder.emailEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setEmailAddress(ViewUtil.getLayoutEditTextValue(holder.emailEditText)));

        holder.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentItem.getAddress().setCountry(String.valueOf(adapterView.getSelectedItemPosition()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        holder.headerSection.setOnClickListener(v -> {
            int currentVisibility = holder.contentSection.getVisibility();
            holder.contentSection.setVisibility(currentVisibility == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> {
            currentItem.setFromDate(getLayoutEditTextValue(charSequence.toString()));
            holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());
        });
        holder.toDateEditText.addTextChangedListener(
                (CustomTextWatcher) (charSequence, i, i1, i2) -> {
                    currentItem.setToDate(getLayoutEditTextValue(charSequence.toString()));
                    holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());
                });
    }

    @Override
    public int getItemCount() {
        return employmentItems.size();
    }

    public boolean validate(View v) {
        boolean isValid = true;
//        EditText countryEditText = v.findViewById(R.id.country_edit_text);
//        EditText streetEditText = v.findViewById(R.id.street_edit_text);
        EditText stateEditText = v.findViewById(R.id.state_edit_text);
        EditText suburbEditText = v.findViewById(R.id.suburb_edit_text);
        EditText postCodeEditText = v.findViewById(R.id.post_code_edit_text);
        EditText fromDateEditText = v.findViewById(R.id.from_date_view);

//        if(TextUtils.isEmpty(countryEditText.getText())){
//            countryEditText.setError("This field is required");
//            isValid = false;
//        }
//
//        if(TextUtils.isEmpty(streetEditText.getText())){
//            streetEditText.setError("This field is required");
//            isValid = false;
//        }

        if(TextUtils.isEmpty(stateEditText.getText())){
            stateEditText.setError("This field is required");
            isValid = false;
        }

        if(TextUtils.isEmpty(suburbEditText.getText())){
            suburbEditText.setError("This field is required");
            isValid = false;
        }

        if(TextUtils.isEmpty(postCodeEditText.getText())){
            postCodeEditText.setError("This field is required");
            isValid = false;
        }

        if(TextUtils.isEmpty(fromDateEditText.getText())){
            fromDateEditText.setError("This field is required");
            isValid = false;
        }

        return isValid;
    }
}
