package com.AndoidApp.ProductsApplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class OpenedDialog extends AppCompatDialogFragment {

    private String title, content;

    public OpenedDialog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(title)
                .setMessage(content)
                .setPositiveButton("Seen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return alert.create();
    }
}
