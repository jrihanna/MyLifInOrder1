package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;
import static com.example.mylifeinorder1.util.ViewUtil.showDateOnSelect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.model.Loan;
import com.example.mylifeinorder1.model.enums.LoanType;
import com.example.mylifeinorder1.model.enums.PaymentPer;
import com.example.mylifeinorder1.util.CustomTextWatcher;

import java.math.BigDecimal;
import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

    List<Loan> loanList;

    private LoanAdapter.OnDeleteItemClickListener mDeleteListener;

    public interface OnDeleteItemClickListener {
        void onItemClick(int position);
    }

    public void setOnDeleteItemClickListener(LoanAdapter.OnDeleteItemClickListener listener) {
        mDeleteListener = listener;
    }

    public static class LoanViewHolder extends RecyclerView.ViewHolder {
        public EditText fromDateEditText;

        public EditText toDateEditText;

        public EditText loanInstituteNameEditText;

        public EditText amount;

        public EditText repaymentAmount;

        public Spinner paymentType;

        public Spinner loanType;

        public RelativeLayout contentSection;

        public RelativeLayout headerSection;

        public TextView cardTitle;

        public ImageButton deleteButton;

        public LoanViewHolder(@NonNull View itemView, LoanAdapter.OnDeleteItemClickListener listener) {
            super(itemView);
            fromDateEditText = itemView.findViewById(R.id.from_date_view);
            toDateEditText = itemView.findViewById(R.id.to_date_view);
            loanInstituteNameEditText = itemView.findViewById(R.id.company_name_edit_text);

            loanType = itemView.findViewById(R.id.loan_type_spinner);
            amount = itemView.findViewById(R.id.amount_edit_text);
            repaymentAmount = itemView.findViewById(R.id.amount);

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

    public LoanAdapter(List<Loan> loanList) {
        this.loanList = loanList;
    }

    @NonNull
    @Override
    public LoanAdapter.LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_item, parent, false);
        LoanAdapter.LoanViewHolder rvh = new LoanAdapter.LoanViewHolder(v, mDeleteListener);
        return rvh;
    }


    @Override
    public void onBindViewHolder(@NonNull LoanAdapter.LoanViewHolder holder, int position) {
        Loan currentItem = loanList.get(position);

        holder.loanInstituteNameEditText.setText(currentItem.getName());
        holder.loanInstituteNameEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setName(getLayoutEditTextValue(holder.loanInstituteNameEditText)));

        holder.fromDateEditText.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate());
        holder.toDateEditText.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate());

        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(getLayoutEditTextValue(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(getLayoutEditTextValue(holder.toDateEditText)));

        holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());

        holder.amount.setText(currentItem.getInitialAmount() == null ? "" : currentItem.getInitialAmount().toString());
        holder.amount.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setInitialAmount(new BigDecimal(getLayoutEditTextValue(holder.amount))));

        holder.repaymentAmount.setText(currentItem.getRepaymentAmount().toString());
        holder.repaymentAmount.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setRepaymentAmount(new BigDecimal(getLayoutEditTextValue(holder.repaymentAmount))));

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

        holder.loanType.setSelection(LoanType.getIndex(currentItem.getLoanType()));
        holder.paymentType.setSelection(PaymentPer.getIndex(currentItem.getPer()));
        holder.loanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentItem.setLoanType(adapterView.getSelectedItemPosition());
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
        return loanList.size();
    }

    public boolean validate(View v) {
        boolean isValid = true;


        return isValid;
    }
}
