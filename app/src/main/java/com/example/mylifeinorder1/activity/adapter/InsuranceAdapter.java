package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;
import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValueToBigDecimal;
import static com.example.mylifeinorder1.util.ViewUtil.showDateOnSelect;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.model.Employment;
import com.example.mylifeinorder1.model.Insurance;
import com.example.mylifeinorder1.model.enums.InsuranceType;
import com.example.mylifeinorder1.model.enums.PaymentPer;
import com.example.mylifeinorder1.util.CustomTextWatcher;

import java.math.BigDecimal;
import java.util.List;

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.InsuranceViewHolder> {

    List<Insurance> insuranceList;

    private InsuranceAdapter.OnDeleteItemClickListener mDeleteListener;

    public interface OnDeleteItemClickListener {
        void onItemClick(int position);
    }

    public void setOnDeleteItemClickListener(InsuranceAdapter.OnDeleteItemClickListener listener) {
        mDeleteListener = listener;
    }

    public static class InsuranceViewHolder extends RecyclerView.ViewHolder {

        public EditText fromDateEditText;

        public EditText toDateEditText;

        public EditText companyNameEditText;

        public EditText amount;

        public Spinner insuranceType;

        public Spinner paymentType;

//        public EditText phoneEditText;
//
//        public EditText emailEditText;

        public RelativeLayout contentSection;

        public RelativeLayout headerSection;

        public TextView cardTitle;

        public ImageButton deleteButton;

        public InsuranceViewHolder(@NonNull View itemView, InsuranceAdapter.OnDeleteItemClickListener listener) {
            super(itemView);
            fromDateEditText = itemView.findViewById(R.id.from_date_view);
            toDateEditText = itemView.findViewById(R.id.to_date_view);
            companyNameEditText = itemView.findViewById(R.id.company_name_edit_text);

            insuranceType = itemView.findViewById(R.id.insurance_type_spinner);
            amount = itemView.findViewById(R.id.amount);

            paymentType = itemView.findViewById(R.id.payment_type_spinner);

//            phoneEditText = itemView.findViewById(R.id.phone_edit_text);
//            emailEditText = itemView.findViewById(R.id.email_edit_text);

            contentSection = itemView.findViewById(R.id.content_section);
            headerSection = itemView.findViewById(R.id.header_section);
            cardTitle = itemView.findViewById(R.id.card_title);
            deleteButton = itemView.findViewById(R.id.delete_button);

            showDateOnSelect(fromDateEditText, itemView.getContext());
            showDateOnSelect(toDateEditText, itemView.getContext());

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

    public InsuranceAdapter(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    @NonNull
    @Override
    public InsuranceAdapter.InsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_item, parent, false);
        InsuranceAdapter.InsuranceViewHolder rvh = new InsuranceAdapter.InsuranceViewHolder(v, mDeleteListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull InsuranceAdapter.InsuranceViewHolder holder, int position) {
        Insurance currentItem = insuranceList.get(position);

        holder.companyNameEditText.setText(currentItem.getName());
//        holder.phoneEditText.setText(currentItem.getNumber());
//        holder.emailEditText.setText(currentItem.getEmailAddress());

        holder.fromDateEditText.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate());
        holder.toDateEditText.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate());

        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(getLayoutEditTextValue(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(getLayoutEditTextValue(holder.toDateEditText)));

        holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());

        holder.amount.setText(currentItem.getAmount().toString());
        holder.amount.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setAmount(getLayoutEditTextValueToBigDecimal(charSequence.toString())));

        holder.companyNameEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setName(getLayoutEditTextValue(holder.companyNameEditText)));
//        holder.phoneEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setNumber(getLayoutEditTextValue(holder.phoneEditText)));
//        holder.emailEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setEmailAddress(getLayoutEditTextValue(holder.emailEditText)));

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
                    holder.cardTitle.setText(holder.toDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());
                });

        holder.insuranceType.setSelection(InsuranceType.getIndex(currentItem.getInsuranceType()));
        holder.paymentType.setSelection(PaymentPer.getIndex(currentItem.getPer()));
        holder.insuranceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentItem.setInsuranceType(adapterView.getSelectedItemPosition());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        holder.paymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    currentItem.setPer(adapterView.getSelectedItemPosition());
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });
    }

    @Override
    public int getItemCount() {
        return insuranceList.size();
    }

    public boolean validate(View v) {
        boolean isValid = true;


        return isValid;
    }
}
