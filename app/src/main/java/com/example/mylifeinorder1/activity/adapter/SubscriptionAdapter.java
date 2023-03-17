package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;
import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValueToBigDecimal;
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
import com.example.mylifeinorder1.model.Subscription;
import com.example.mylifeinorder1.model.enums.LoanType;
import com.example.mylifeinorder1.model.enums.PaymentPer;
import com.example.mylifeinorder1.util.CustomTextWatcher;

import java.math.BigDecimal;
import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {

    List<Subscription> subscriptionList;

    private SubscriptionAdapter.OnDeleteItemClickListener mDeleteListener;

    public interface OnDeleteItemClickListener {
        void onItemClick(int position);
    }

    public void setOnDeleteItemClickListener(SubscriptionAdapter.OnDeleteItemClickListener listener) {
        mDeleteListener = listener;
    }

    public static class SubscriptionViewHolder extends RecyclerView.ViewHolder {
        public EditText fromDateEditText;

        public EditText toDateEditText;

        public EditText subscriberNameEditText;

        public EditText amount;

        public Spinner paymentType;

        public RelativeLayout contentSection;

        public RelativeLayout headerSection;

        public TextView cardTitle;

        public ImageButton deleteButton;

        public SubscriptionViewHolder(@NonNull View itemView, SubscriptionAdapter.OnDeleteItemClickListener listener) {
            super(itemView);
            
            fromDateEditText = itemView.findViewById(R.id.from_date_view);
            toDateEditText = itemView.findViewById(R.id.to_date_view);
            subscriberNameEditText = itemView.findViewById(R.id.subscriber_name_edit_text);
            amount = itemView.findViewById(R.id.amount);

            paymentType = itemView.findViewById(R.id.payment_type_spinner);

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

    public SubscriptionAdapter(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    @NonNull
    @Override
    public SubscriptionAdapter.SubscriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_item, parent, false);
        SubscriptionAdapter.SubscriptionViewHolder rvh = new SubscriptionAdapter.SubscriptionViewHolder(v, mDeleteListener);
        return rvh;
    }


    @Override
    public void onBindViewHolder(@NonNull SubscriptionAdapter.SubscriptionViewHolder holder, int position) {
        Subscription currentItem = subscriptionList.get(position);

        holder.subscriberNameEditText.setText(currentItem.getName());
        holder.subscriberNameEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setName(getLayoutEditTextValue(holder.subscriberNameEditText)));

        holder.fromDateEditText.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate());
        holder.toDateEditText.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate());

        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(getLayoutEditTextValue(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(getLayoutEditTextValue(holder.toDateEditText)));

        holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());

        holder.amount.setText(currentItem.getAmount() == null ? "" : currentItem.getAmount().toString());
        holder.amount.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setAmount(getLayoutEditTextValueToBigDecimal(charSequence.toString())));


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

        holder.paymentType.setSelection(PaymentPer.getIndex(currentItem.getPer()));

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
        return subscriptionList.size();
    }

    public boolean validate(View v) {
        boolean isValid = true;


        return isValid;
    }

}
