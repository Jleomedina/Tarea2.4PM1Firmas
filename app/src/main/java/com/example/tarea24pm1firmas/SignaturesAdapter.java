package com.example.tarea24pm1firmas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SignaturesAdapter extends RecyclerView.Adapter<SignaturesAdapter.ViewHolder> {

    private List<Signatures> signatureList;
    private Context context;

    public SignaturesAdapter(Context context, List<Signatures> signatureList) {
        this.context = context;
        this.signatureList = signatureList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Signatures signature = signatureList.get(position);

        // Set description
        holder.tvDescription.setText(signature.getDescription());

        // Set signature image (you need to decode the byte array)
        Bitmap signatureBitmap = BitmapFactory.decodeByteArray(
                signature.getDigitalSignature(), 0, signature.getDigitalSignature().length
        );
        holder.ivSignature.setImageBitmap(signatureBitmap);
    }

    @Override
    public int getItemCount() {
        return signatureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription;
        ImageView ivSignature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivSignature = itemView.findViewById(R.id.ivSignature);
        }
    }
}

