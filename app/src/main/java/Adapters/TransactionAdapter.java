package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.Supermarket.R;
import com.example.Supermarket.TransactionActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import Models.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private ArrayList<Transaction> transactionArrayList = new ArrayList<>();
    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_transactionDate, tv_transaction_totalPrice, tv_transaction_quantity, tv_transactionID;

        public ViewHolder(View view) {
            super(view);
            tv_transactionDate = (TextView) view.findViewById(R.id.tv_transactionDate);
            tv_transaction_totalPrice = (TextView) view.findViewById(R.id.tv_transaction_totalPrice);
            tv_transaction_quantity = (TextView) view.findViewById(R.id.tv_transaction_quantity);
            tv_transactionID = (TextView) view.findViewById(R.id.tv_transaction_id);


        }

        public TextView getTextView() {
            return null;
        }

    }

    public TransactionAdapter(Context ctx) {
        this.transactionArrayList = TransactionActivity.transactionsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_transaction, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        int transactionID = transactionArrayList.get(position).getTransactionID();
        int totalQuantity = transactionArrayList.get(position).getCount();
        int totalPrice = transactionArrayList.get(position).getTotalPrice();
        String transactionDate = transactionArrayList.get(position).getTransactionDate();

        String priceText = "Total: Rp" + (NumberFormat.getNumberInstance(Locale.US).format(totalPrice));
        String quantityText = "Total Quantity: " + Integer.toString(totalQuantity);


        viewHolder.tv_transaction_quantity.setText(quantityText);
        viewHolder.tv_transaction_totalPrice.setText(priceText);
        viewHolder.tv_transactionDate.setText(transactionDate);
        viewHolder.tv_transactionID.setText(Integer.toString(transactionID));
    }

    @Override
    public int getItemCount() {
        return transactionArrayList.size();
    }
}
