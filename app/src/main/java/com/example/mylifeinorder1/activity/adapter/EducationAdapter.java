package com.example.mylifeinorder1.activity.adapter;

import static com.example.mylifeinorder1.util.ViewUtil.getLayoutEditTextValue;
import static com.example.mylifeinorder1.util.ViewUtil.showDateOnSelect;

import android.text.TextUtils;
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
import com.example.mylifeinorder1.model.Education;
import com.example.mylifeinorder1.model.Employment;
import com.example.mylifeinorder1.util.CustomTextWatcher;
import com.example.mylifeinorder1.util.ViewUtil;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {

    private List<Education> educationList;

    private EducationAdapter.OnDeleteItemClickListener mDeleteListener;

    public interface OnDeleteItemClickListener {
        void onItemClick(int position);
    }
    public void setOnDeleteItemClickListener(EducationAdapter.OnDeleteItemClickListener listener) {
        mDeleteListener = listener;
    }

    public static class EducationViewHolder extends RecyclerView.ViewHolder {

        public Spinner countrySpinner;
        public EditText streetEditText;
        public EditText line2EditText;
        public EditText suburbEditText;
        public EditText stateEditText;
        public EditText postCodeEditText;
        public EditText fromDateEditText;
        public EditText toDateEditText;

        public EditText nameEditText;

        public EditText fieldOfStudyEditText;
        public EditText gradeEditText;

        public EditText phoneEditText;

        public EditText emailEditText;

        public RelativeLayout contentSection;
        public RelativeLayout headerSection;

        public TextView cardTitle;

        public ImageButton deleteButton;


        public EducationViewHolder(@NonNull View itemView, EducationAdapter.OnDeleteItemClickListener listener) {
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
            fieldOfStudyEditText = itemView.findViewById(R.id.field_study_edit_text);
            gradeEditText = itemView.findViewById(R.id.grade_edit_text);
            phoneEditText = itemView.findViewById(R.id.phone_edit_text);
            emailEditText = itemView.findViewById(R.id.email_edit_text);

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

    public EducationAdapter(List<Education> educationList) {
        this.educationList = educationList;
    }

    @NonNull
    @Override
    public EducationAdapter.EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item, parent, false);
        EducationAdapter.EducationViewHolder rvh = new EducationAdapter.EducationViewHolder(v, mDeleteListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull EducationAdapter.EducationViewHolder holder, int position) {
        Education currentItem = educationList.get(position);

        holder.countrySpinner.setSelection(currentItem.getAddress().getCountry() == null ? 0 : Integer.valueOf(currentItem.getAddress().getCountry()));
        holder.streetEditText.setText(currentItem.getAddress().getStreet());
        holder.line2EditText.setText(currentItem.getAddress().getLine2());
        holder.suburbEditText.setText(currentItem.getAddress().getSuburb());
        holder.stateEditText.setText(currentItem.getAddress().getState());
        holder.postCodeEditText.setText(currentItem.getAddress().getPostCode());

        holder.nameEditText.setText(currentItem.getName());
        holder.phoneEditText.setText(currentItem.getNumber());
        holder.emailEditText.setText(currentItem.getEmailAddress());

        holder.fieldOfStudyEditText.setText(currentItem.getFieldOfStudy());
        holder.gradeEditText.setText(currentItem.getGrade());

        holder.fromDateEditText.setText(currentItem.getFromDate() == null ? "" : currentItem.getFromDate().toString());
        holder.toDateEditText.setText(currentItem.getToDate() == null ? "" : currentItem.getToDate().toString());

//        holder.countryEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setCountry(getLayoutEditTextValue(holder.countryEditText)));
        holder.streetEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setStreet(getLayoutEditTextValue(holder.streetEditText)));
        holder.line2EditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setLine2(getLayoutEditTextValue(holder.line2EditText)));
        holder.suburbEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setSuburb(getLayoutEditTextValue(holder.suburbEditText)));
        holder.stateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setState(getLayoutEditTextValue(holder.stateEditText)));
        holder.postCodeEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.getAddress().setPostCode(getLayoutEditTextValue(holder.postCodeEditText)));
        holder.fromDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFromDate(getLayoutEditTextValue(holder.fromDateEditText)));
        holder.toDateEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setToDate(getLayoutEditTextValue(holder.toDateEditText)));

        holder.nameEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setName(getLayoutEditTextValue(holder.nameEditText)));
        holder.fieldOfStudyEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setFieldOfStudy(getLayoutEditTextValue(holder.fieldOfStudyEditText)));
        holder.gradeEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setGrade(getLayoutEditTextValue(holder.gradeEditText)));
        holder.phoneEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setNumber(getLayoutEditTextValue(holder.phoneEditText)));
        holder.emailEditText.addTextChangedListener((CustomTextWatcher) (charSequence, i, i1, i2) -> currentItem.setEmailAddress(getLayoutEditTextValue(holder.emailEditText)));

        holder.cardTitle.setText(holder.fromDateEditText.getText().toString() + " - " + holder.toDateEditText.getText().toString());

        holder.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentItem.getAddress().setCountry(String.valueOf(adapterView.getSelectedItemPosition()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
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

        holder.headerSection.setOnClickListener(v -> {
            int currentVisibility = holder.contentSection.getVisibility();
            holder.contentSection.setVisibility(currentVisibility == View.VISIBLE ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public boolean validate(View v) {
        boolean isValid = true;
//        EditText countryEditText = v.findViewById(R.id.country_edit_text);
        EditText streetEditText = v.findViewById(R.id.street_edit_text);
        EditText stateEditText = v.findViewById(R.id.state_edit_text);
        EditText suburbEditText = v.findViewById(R.id.suburb_edit_text);
        EditText postCodeEditText = v.findViewById(R.id.post_code_edit_text);
        EditText fromDateEditText = v.findViewById(R.id.from_date_view);

//        if(TextUtils.isEmpty(countryEditText.getText())){
//            countryEditText.setError("This field is required");
//            isValid = false;
//        }

        if(TextUtils.isEmpty(streetEditText.getText())){
            streetEditText.setError("This field is required");
            isValid = false;
        }

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
